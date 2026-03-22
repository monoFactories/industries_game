package mono.factories.engines.process;

import mono.factories.engines.process.configurable.ConfigurableProcessParameter;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.RegistryRegistries;
import mono.factories.registries.registry.StandardRegistry;

public class ProcessRegistries {
    public static final Registry<ConfigurableProcessParameter> PROCESS_PARAMETERS = new StandardRegistry<>();
}
