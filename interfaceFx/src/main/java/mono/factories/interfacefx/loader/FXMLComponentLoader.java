package mono.factories.interfacefx.loader;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mono.factories.interfacefx.components.Component;
import mono.factories.interfacefx.nodeparameters.NodeParameters;
import mono.factories.interfacefx.nodeparameters.NodeParametersParser;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

public class FXMLComponentLoader {

    public static Component parse(InputStream fxmlInput, Identifier componentId, Function<Storage2<AnchorPane, Identifier>, Component> componentFunction) {
        Objects.requireNonNull(fxmlInput, "fxmlInput is null");
        Objects.requireNonNull(componentId, "componentId is null");
        Objects.requireNonNull(componentFunction, "componentFunction is null");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            AnchorPane anchorPane = fxmlLoader.load(fxmlInput);
            AtomicReference<Consumer<Node>> parseJsonParameter_ = new AtomicReference<>();
            Consumer<Node> parseJsonParameter = node -> {
                if (node instanceof Parent parent) {
                    parent.getChildrenUnmodifiable().forEach(node1 -> parseJsonParameter_.get().accept(node1));
                }
                if (node.getUserData() instanceof String str) {
                    NodeParameters nodeParameters = NodeParametersParser.parserGson.fromJson(str, NodeParameters.class);
                    node.setUserData(nodeParameters);
                }
            };
            parseJsonParameter_.set(parseJsonParameter);
            parseJsonParameter.accept(anchorPane);
            return componentFunction.apply(new Storage2<>(anchorPane, componentId));
        } catch (Exception e) {
            return null;
        }
    }
}
