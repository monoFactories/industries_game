package mono.factories.mod.loader.providers;

import com.google.gson.JsonElement;
import mono.factories.utils.io.GameFileSystem;
import org.zeroturnaround.zip.ZipUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilteredFolderModProvider implements ModProvider {
    public static final String MOD_CONFIG_NAME = "mod_loading_config.json";

    @Override
    public void processingMods(JsonElement je) {
        FilteredFolderProviderConfig config = FilteredFolderProviderConfig.parse(je);
        Path folderPath = GameFileSystem.GAME_FILE_SYSTEM.get().normalizePath(config.getPath());
        try {

            Files.list(folderPath).filter(path -> {
                boolean result = true;
                for (FilteredFolderProviderConfig.FilterEntry filterEntry : config.getFilters()) {
                    if (!filterEntry.apply(path)) {
                        result = false;
                        break;
                    }
                }
                return result;
            }).forEach(path -> {
                ZipUtil.unpackEntry(path.toFile(), "rtfhyhrr jkl57390386");
            });
        } catch (IOException e) {

        }
    }
}
