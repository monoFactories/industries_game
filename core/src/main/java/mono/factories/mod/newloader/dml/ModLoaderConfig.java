package mono.factories.mod.newloader.dml;

import com.google.gson.*;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;

public record ModLoaderConfig(Identifier loaderId, ModID id, Registry<JsonElement> information, Registry<DependencyEntry> dependencies) {
    static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ModLoaderConfig.class, (JsonDeserializer<ModLoaderConfig>) (json, type, context) -> {
                JsonObject o = json.getAsJsonObject();
                Identifier loaderId = null;
                JsonElement jsonLoader = o.get("loader");
                if (jsonLoader != null) {
                    try {
                        loaderId = new Identifier(jsonLoader.getAsString());
                    } catch (Exception e) {
                        //log
                    }
                }
                ModID modID = context.deserialize(o.get("id"), ModID.class);
                Registry<JsonElement> info = new DefaultRegistry<>();
                try {
                    o.get("information").getAsJsonObject().asMap().forEach((str, je) -> info.register(new Identifier(str), je));
                } catch (Exception e) {
                    //
                }
                Registry<DependencyEntry> dependencies = DependencyEntry.createMap(o.get("dependencies"));
                return new ModLoaderConfig(DMLConstants.DEFAULT_MOD_LOADER_ID.getOrDefault(loaderId), modID, info, dependencies);
            })
            .registerTypeAdapter(ModID.class, ModID.deserializer)
            .create();
}
/*
{
    "loader": "id_of_loader" #optional
    "id": {
        "id": "mod_id"
        "path": "mod"
    },
    "information": {
        "key_id": "JsonElement"
    },
    "dependencies": {
        "dependency_id": {
            "path": "mod",
            "version": ">=0.7.19"
        }
    }
*/