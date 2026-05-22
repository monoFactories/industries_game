package mono.factories.mod.newloader.api.collections;

import mono.factories.mod.newloader.level.LoaderLevel;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.Storage2;

public final class LoaderLevels {
    private LoaderLevels() {
    }

    public static final Registry<LoaderLevel> loaderLevels = new DefaultRegistry<>();
    public static final Storage2<Identifier, LoaderLevel> FOLDER_LOADER_LEVEL;

    static {
        FOLDER_LOADER_LEVEL = loaderLevels.registerStorage(new Identifier("folder_loader_level"), ((data, progress) -> {

        }));
    }
}
