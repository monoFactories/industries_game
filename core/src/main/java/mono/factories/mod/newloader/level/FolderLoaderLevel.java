package mono.factories.mod.newloader.level;

import com.google.gson.*;
import mono.factories.mod.newloader.api.LoadingProgress;
import mono.factories.mod.newloader.source.CodeSourceFactory;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.mod.newloader.source.provider.CodeSourceProvider;
import mono.factories.utils.io.GameFileSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class FolderLoaderLevel implements LoaderLevel {
    private static final Logger logger = LogManager.getLogger(FolderLoaderLevel.class);
    private static final Gson deserializer;
    @Override
    public void startLoading(JsonElement data, LoadingProgress progress) {
        Config config = deserializer.fromJson(data, Config.class);
        GameFileSystem gfs = GameFileSystem.GAME_FILE_SYSTEM.get();
        Path path = gfs.normalizePath(config.path());
        try {
            Files.list(path).forEach(path1 -> {
                CodeSourceProvider sourceProvider = CodeSourceFactory.factoryHolder.get().get().chooseProvider(path1);
                try {
                    CodeSource codeSource = sourceProvider.create(path1);
                    
                } catch (Exception e) {
                    logger.warn("creation codeSource failed to path: {}", path1, e);
                }
            });
        } catch (IOException e) {
            logger.warn("iteration on directory: {} failed", path, e);
        }
    }

    private record Config(String path) {
    }
    private static final class ConfigDeserializer implements JsonDeserializer<Config> {
        @Override
        public Config deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject o = json.getAsJsonObject();
            String path = o.get("path").getAsString();
            return new Config(path);
        }
    }

    static {
        deserializer = new GsonBuilder()
                .registerTypeAdapter(Config.class, new ConfigDeserializer())
                .create();
    }
    /*
    {
        "folder": "path_to_folder"
    }
     */
}
