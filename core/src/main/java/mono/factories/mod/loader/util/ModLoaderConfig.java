package mono.factories.mod.loader.util;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Supplier;

public class ModLoaderConfig implements Identifiable {
    private static final Supplier<Gson> getGson;
    private final Identifier configId;
    private final Map<Identifier, JsonElement> suppliers;
    private final Map<Identifier, Boolean> states;

    public ModLoaderConfig(Identifier configId, Map<Identifier, JsonElement> suppliers, Map<Identifier, Boolean> states) {
        this.configId = configId;
        this.suppliers = suppliers;
        this.states = states;// United States
    }

    public Map<Identifier, JsonElement> getSuppliers() {
        return suppliers;
    }

    public Map<Identifier, Boolean> getStates() {
        return states;
    }

    @Override
    public Identifier id() {
        return configId;
    }

    public static ModLoaderConfig parse(String content) {
        Gson gson = getGson.get();
        return gson.fromJson(content, ModLoaderConfig.class);
    }

    private static final class ModLoaderConfigDeserializer implements JsonDeserializer<ModLoaderConfig> {
        @Override
        public ModLoaderConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject o = json.getAsJsonObject();
            Identifier configId = context.deserialize(o.get("configId"), Identifier.class);
            Map<Identifier, JsonElement> fileSuppliers = new Object2ObjectOpenHashMap<>();
            o.get("fileSuppliers").getAsJsonObject().asMap().forEach((s, je) -> fileSuppliers.put(new Identifier(s), je));
            Map<Identifier, Boolean> states = new Object2ObjectOpenHashMap<>();
            o.get("fileSuppliers").getAsJsonObject().asMap().forEach((s, je) -> states.put(new Identifier(s), je.getAsBoolean()));
            return new ModLoaderConfig(configId, fileSuppliers, states);
        }
    }
    static {
        getGson = () -> new GsonBuilder()
                .registerTypeAdapter(Identifier.class, Identifier.JSON)
                .registerTypeAdapter(ModLoaderConfig.class, new ModLoaderConfigDeserializer())
                .create();
    }
}
/*
{
    "configId": "?",
    "fileSuppliers": {
        "supplierID": {"element for supplier"}
    },
    "states": { // turns off/on the suppliers
        "supplierID": true
    }
}
*/