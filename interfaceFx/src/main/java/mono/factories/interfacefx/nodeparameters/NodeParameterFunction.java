package mono.factories.interfacefx.nodeparameters;

import javafx.scene.Node;
import mono.factories.interfacefx.components.Component;

public interface NodeParameterFunction {
    void execute(Node node, NodeParameters parameters, Component component);
}
