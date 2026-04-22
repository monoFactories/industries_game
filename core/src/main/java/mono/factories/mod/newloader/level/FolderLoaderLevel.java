package mono.factories.mod.newloader.level;

import com.google.gson.*;
import mono.factories.mod.newloader.api.LoadingProgress;
import mono.factories.utils.io.GameFileSystem;

import java.lang.reflect.Type;
import java.nio.file.Path;

public class FolderLoaderLevel implements LoaderLevel {
    private static final Gson deserializer;
    @Override
    public void startLoading(JsonElement data, LoadingProgress progress) {
        Config config = deserializer.fromJson(data, Config.class);
        GameFileSystem gfs = GameFileSystem.GAME_FILE_SYSTEM.get();
        Path path = gfs.normalizePath(config.path());

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
