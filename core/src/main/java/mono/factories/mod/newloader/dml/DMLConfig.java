package mono.factories.mod.newloader.dml;

import com.google.gson.JsonElement;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;

public record DMLConfig(Identifier customLoader, String id, Registry<JsonElement> information, Registry<DependencyEntry> dependencies) {

}
/*
{
    "custom_loader": "id_of_loader" #optional
    "id": "id",
    "information": {
        "key_id": "JsonElement"
    },
    "dependencies": {
        ...
    }
*/