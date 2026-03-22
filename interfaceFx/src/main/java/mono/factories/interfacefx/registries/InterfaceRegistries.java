package mono.factories.interfacefx.registries;

import mono.factories.interfacefx.components.Component;
import mono.factories.interfacefx.nodeparameters.NodeParameterFunction;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class InterfaceRegistries {
    public static final Registry<NodeParameterFunction> NODE_PARAMETERS_FUNCTION = new StandardRegistry<>();
    public static final Registry<Component> UI_COMPONENTS = new StandardRegistry<>();
}
