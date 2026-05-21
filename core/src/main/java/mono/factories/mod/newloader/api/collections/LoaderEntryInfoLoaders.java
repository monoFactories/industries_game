package mono.factories.mod.newloader.api.collections;

import mono.factories.mod.newloader.api.LoaderEntryInfo;
import mono.factories.mod.newloader.api.LoaderEntryInfoLoader;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

import java.io.IOException;

public class LoaderEntryInfoLoaders {
    public static final Registry<LoaderEntryInfoLoader> infoLoaders = new StandardRegistry<>();
    public static final LoaderEntryInfoLoader CONFIG_FILE_LOADER;
    public static final String DEFAULT_LOADER_CONFIG_NAME = "loader_config.json";

    public static LoaderEntryInfo tryLoad(CodeSource source) {
        LoaderEntryInfo infoReference = null;
        for (LoaderEntryInfoLoader infoLoader : infoLoaders) {
            try {
                LoaderEntryInfo info = infoLoader.load(source);
                if (info != null) {
                    infoReference = info;
                    break;
                }
            } catch (Exception e) {
                //write log
            }
        }
        return infoReference;
    }

    static {
        CONFIG_FILE_LOADER = codeSource -> {
            try {
                String json = codeSource.readAsString(DEFAULT_LOADER_CONFIG_NAME);
                return LoaderEntryInfo.parse(json);
            } catch (IOException e) {
                return null;
            }
        };
    }
}
