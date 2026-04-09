package mono.factories.mod.loader.providers;

import com.google.gson.JsonElement;

public interface ModProvider {
    void processingMods(JsonElement je);
}
