package mono.factories.engines.process;

import mono.factories.engines.process.configurable.ConfigurableProcessParameter;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;

public class ProcessRegistries {
    public static final Registry<ConfigurableProcessParameter> PROCESS_PARAMETERS = new DefaultRegistry<>();
}
