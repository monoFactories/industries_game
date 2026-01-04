package mono.factories.interfacefx.registries;

import mono.factories.interfacefx.nodeparameters.NodeParameterFunction;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.RegistryRegistries;
import mono.factories.registries.registry.StandardRegistry;

public class InterfaceRegistries {
    public static final Registry<NodeParameterFunction> NODE_PARAMETERS_FUNCTION_REGISTRY = RegistryRegistries.register(new StandardRegistry<>(), new Identifier("node_parameter_function"));
}
