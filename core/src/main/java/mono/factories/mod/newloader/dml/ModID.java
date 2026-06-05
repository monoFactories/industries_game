package mono.factories.mod.newloader.dml;

import com.google.gson.*;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.DefaultHolder;

import java.lang.reflect.Type;

public record ModID(Identifier path, String id) {
    public static final DefaultHolder<Identifier> DEFAULT_PATH = new DefaultHolder<>(ModRegisters.MODS.a());
    static final JsonDeserializer<ModID> deserializer = (json, typeOfT, context) -> {
        JsonObject o = new JsonObject();
        Identifier path = null;
        if (o.has("path")) {
            try {
                path = new Identifier(o.get("path").getAsString());
            } catch (Exception e) {
                //
            }
        }
        return new ModID(DEFAULT_PATH.getOrDefault(path), o.get("id").getAsString());
    };
}
/*
"id": {
    "path": "mod",
    "id": "id_of_mod"
}
 */