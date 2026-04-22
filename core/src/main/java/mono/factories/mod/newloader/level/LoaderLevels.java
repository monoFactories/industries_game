package mono.factories.mod.newloader.level;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import static mono.factories.mod.newloader.level.LoaderLevel.loaderLevels;

public final class LoaderLevels {
    private LoaderLevels() {}
    public static final Registry<LoaderLevel> loaderLevels = new StandardRegistry<>();
    public static final Storage2<Identifier, LoaderLevel> FOLDER_LOADER_LEVEL;

    static {
        FOLDER_LOADER_LEVEL = loaderLevels.registerStorage(new Identifier("folder_loader_level"), ((data, progress) -> {
            
        }));
    }
}
