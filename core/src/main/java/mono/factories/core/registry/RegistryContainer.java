package mono.factories.core.registry;

import mono.factories.engines.process.ProcessRegistries;
import mono.factories.engines.process.configurable.ConfigurableProcessParameter;
import mono.factories.interfacefx.components.Component;
import mono.factories.interfacefx.nodeparameters.NodeParameterFunction;
import mono.factories.interfacefx.registries.InterfaceRegistries;
import mono.factories.languages.language.LanguageRegistries;
import mono.factories.mod.Mod;
import mono.factories.mod.registries.ModRegistries;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.RegistryRegistries;
import mono.factories.registries.storage.Storage2;

public class RegistryContainer {
    public static final Storage2<Registry<String>, Identifier> LANG_OPCODES = reg(LanguageRegistries.LANG_OPCODES, RegistryKeys.LANG_OPCODES);
    public static final Storage2<Registry<NodeParameterFunction>, Identifier> NODE_PARAMETERS_FUNCTION = reg(InterfaceRegistries.NODE_PARAMETERS_FUNCTION, RegistryKeys.NODE_PARAMETERS_FUNCTIONS);
    public static final Storage2<Registry<ConfigurableProcessParameter>, Identifier> PROCESS_PARAMETERS = reg(ProcessRegistries.PROCESS_PARAMETERS, RegistryKeys.PROCESS_PARAMETERS);
    public static final Storage2<Registry<Mod>, Identifier> MODS = reg(ModRegistries.mods, RegistryKeys.MODS);
    public static final Storage2<Registry<Component>, Identifier> UI_COMPONENTS = reg(InterfaceRegistries.UI_COMPONENTS, RegistryKeys.UI_COMPONENTS);

    private static <T> Storage2<Registry<T>, Identifier> reg(Registry<T> registryRef, Identifier name) {
        RegistryRegistries.register(registryRef, name);
        return new Storage2<>(registryRef, name);
    }
}
