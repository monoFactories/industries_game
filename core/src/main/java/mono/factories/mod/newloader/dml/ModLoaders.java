package mono.factories.mod.newloader.dml;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.Storage2;

public class ModLoaders {
    public static final Registry<ModLoader> modLoaders = new DefaultRegistry<>();

    public static final Storage2<Identifier, ModLoader> DMLoader = modLoaders.registerStorage(new Identifier("DMLoader"), new DMLoader());
}
