package mono.factories.mod.newloader.dml;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.Storage2;

public class ModRegisters {
    public static final Registry<Registry<ModEntry>> modRegisters = new DefaultRegistry<>();

    public static final Storage2<Identifier, Registry<ModEntry>> MODS = modRegisters.registerStorage(new Identifier("mod"), new DefaultRegistry<>());
    public static final Storage2<Identifier, Registry<ModEntry>> LIBS = modRegisters.registerStorage(new Identifier("lib"), new DefaultRegistry<>());
}
