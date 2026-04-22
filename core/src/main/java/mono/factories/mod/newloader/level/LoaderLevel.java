package mono.factories.mod.newloader.level;

import com.google.gson.JsonElement;
import mono.factories.mod.newloader.api.LoadingProgress;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

// loading mods
public interface LoaderLevel {

    void startLoading(JsonElement data, LoadingProgress progress);


}
