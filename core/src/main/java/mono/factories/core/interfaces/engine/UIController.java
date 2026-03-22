package mono.factories.core.interfaces.engine;

import mono.factories.core.registry.RegistryContainer;
import mono.factories.interfacefx.components.Component;
import mono.factories.registries.id.Identifier;

import java.util.function.Consumer;

public class UIController {
    private static UIEngine engine;

    // add, remove Component and more
    static void init(UIEngine engine) {
        UIController.engine = engine;
    }

    public static void addComponent(Identifier id) {
        consumer((ui) -> {
            Component c = RegistryContainer.UI_COMPONENTS.a().get(id);
            if (c != null) {
                //
            }
        });
    }

    private static void checkEngine() {
        if (engine == null) throw new IllegalStateException("uiEngine not initialized");
    }

    private static void consumer(Consumer<UIEngine> r) {
        checkEngine();
        synchronized (engine.actionLock) {
            engine.actions.add(r);
        }
    }
}


