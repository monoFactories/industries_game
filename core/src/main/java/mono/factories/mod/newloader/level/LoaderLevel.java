package mono.factories.mod.newloader.level;

import com.google.gson.JsonElement;
import mono.factories.mod.newloader.api.LoadingProgress;

// loading mods
public interface LoaderLevel {

    void startLoading(JsonElement data, LoadingProgress progress);

}
