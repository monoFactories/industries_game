package mono.factories.mod.loader.providers;

import com.google.gson.JsonElement;
import mono.factories.dependencies.DependencyResolver;
import mono.factories.dependencies.HasDependency;
import mono.factories.mod.loader.ModClassLoader;
import mono.factories.mod.loader.configs.ModLoadingConfig;
import mono.factories.mod.loader.configs.StandardModLoadConfig;
import mono.factories.registries.id.Identifier;
import mono.factories.utils.io.GameFileSystem;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FilteredFolderModProvider implements ModProvider {
    public static final String MOD_CONFIG_NAME = "mod_loading_config.json";

    @Override
    public void processingMods(JsonElement je) {
        FilteredFolderProviderConfig config = FilteredFolderProviderConfig.parse(je);
        Path folderPath = GameFileSystem.GAME_FILE_SYSTEM.get().normalizePath(config.getPath());
        try (Stream<Path> list = Files.list(folderPath)) {
            DependencyResolver<ModLoadingEntry> configs = new DependencyResolver<>() {
                @Override
                public synchronized List<ModLoadingEntry> resolve() {
                    forEach(mle -> {
                        ModLoadingConfig config = mle.mlc();
                        config.getDependencies().forEach((str, range) -> {
                            ModLoadingEntry dependencyEntry = get(new Identifier(str));
                            if (dependencyEntry == null) {
                                throw new RuntimeException(
                                        String.format("Dependency not found: mod '%s' is not loaded or not registered in the mod loading system.", str)
                                );
                            }
                            ModLoadingConfig dependencyConfig = dependencyEntry.mlc();
                            if (!range.inRange(dependencyConfig.getVersion())) {
                                throw new RuntimeException(
                                        String.format(
                                                "Incompatible dependency version: " +
                                                        "mod '%s' requires a version in the range %s, " +
                                                        "but the installed version is %s.",
                                                str,
                                                range,
                                                dependencyConfig.getVersion()
                                        )
                                );
                            }
                        });
                    });
                    return super.resolve();
                }
            };
            Stream<ModLoadingEntry> modLoadingConfig = list.filter(path -> {
                boolean result = true;
                for (FilteredFolderProviderConfig.FilterEntry filterEntry : config.getFilters()) {
                    if (!filterEntry.apply(path)) {
                        result = false;
                        break;
                    }
                }
                return result;
            }).map(path -> {
                try {
                    String content = new String(ZipUtil.unpackEntry(path.toFile(), MOD_CONFIG_NAME));
                    return new ModLoadingEntry(path, ModLoadingConfig.parse(content));
                } catch (Exception e) {

                } return null;
            }).filter(Objects::nonNull);
            modLoadingConfig.forEach(mlc -> configs.register(mlc.id(), mlc));
            modLoadingConfig.close();
            List<ModLoadingEntry> sortedLoadingEntries = configs.resolve();

        } catch (IOException e) {

        }
    }

    private record ModLoadingEntry(Path path, ModLoadingConfig mlc) implements HasDependency {

        @Override
        public Identifier[] dependencies() {
            return mlc.dependencies();
        }

        @Override
        public Identifier id() {
            return mlc.id();
        }
    }

    public static final class DefaultModLoaderEntry {
        private final File jarFile;
        private final StandardModLoadConfig loadParameters;

        public DefaultModLoaderEntry(Path path, Identifier modId) {
            jarFile = path.toFile();
            String confJson = new String(ZipUtil.unpackEntry(jarFile, StandardModLoadConfig.MOD_LOAD_CONFIG_FILENAME));
            loadParameters = StandardModLoadConfig.parse(confJson);
        }

        public void startLoading() throws MalformedURLException {
            ModClassLoader.classLoader.addURL(jarFile.toURI().toURL());
            List<String> entryPoints = loadParameters.getEntryPoints();
            entryPoints.forEach(entryPoint -> {});
        }
    }
}
