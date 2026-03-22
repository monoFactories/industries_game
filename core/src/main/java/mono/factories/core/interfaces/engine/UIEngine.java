package mono.factories.core.interfaces.engine;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mono.factories.core.registry.RegistryContainer;
import mono.factories.engines.engine.Engine;
import mono.factories.engines.process.Process;
import mono.factories.interfacefx.components.Component;
import mono.factories.interfacefx.nodeparameters.NodeParameters;
import mono.factories.interfacefx.registries.NodeParametersActionTypes;
import mono.factories.registries.id.Identifier;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public final class UIEngine extends Process {
    private static UIEngine uiEngine;

    final List<Component> active = new ObjectArrayList<>();
    final ComparisonsMap comparisons = new ComparisonsMap();
    final List<Consumer<UIEngine>> actions = new ObjectArrayList<>();
    final Object actionLock = new Object();
    private final Pane rootPane;

    private UIEngine(Stage stage) {
        super(new Identifier("ui_engine"), new Identifier[0]);
        rootPane = new AnchorPane();
        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.show();
    }

    public static UIEngine init(Stage stage) {
        if (uiEngine == null) throw new IllegalStateException("uiEngine has been init");
        uiEngine = new UIEngine(stage);
        UIController.init(uiEngine);
        return uiEngine;
    }

    @Override
    public void run(Engine engine) {
        CountDownLatch cdl = new CountDownLatch(1);
        Platform.runLater(() -> {
            synchronized (actionLock) {
                actions.forEach(r -> r.accept(this));
                actions.clear();
            }
            active.forEach(c -> {
                AnchorPane ap = c.getPane();
                NodeParameters np = NodeParameters.create(ap, false);
                if (np != null) {
                    np.getActions().forEachInType(NodeParametersActionTypes.UPDATE, id -> {
                        RegistryContainer.NODE_PARAMETERS_FUNCTION.a().get(id).execute(ap, np, c);
                    });
                }
            });
            cdl.countDown();
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static UIEngine getInstance() {
        return uiEngine;
    }
}
