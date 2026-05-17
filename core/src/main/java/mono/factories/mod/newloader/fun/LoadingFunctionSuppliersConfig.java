package mono.factories.mod.newloader.fun;

import com.google.gson.*;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class LoadingFunctionSuppliersConfig {
    public static final Gson g;
    public final Registry<ConfigElement> suppliers;

    public LoadingFunctionSuppliersConfig(Registry<ConfigElement> suppliers) {
        this.suppliers = suppliers;
    }

    public static LoadingFunctionSuppliersConfig create(String json) {
        return g.fromJson(json, LoadingFunctionSuppliersConfig.class);
    }

    public record ConfigElement(JsonElement supplierData, JsonElement functionData) {

    }

    static {
        g = new GsonBuilder()
                .registerTypeAdapter(LoadingFunctionSuppliersConfig.class,
                        (JsonDeserializer<LoadingFunctionSuppliersConfig>) (json, typeOfT, context) -> {
                            JsonObject o = json.getAsJsonObject();
                            Registry<ConfigElement> suppliers = new StandardRegistry<>();
                            o.asMap().forEach((str, val) -> suppliers.register(new Identifier(str), context.deserialize(val, ConfigElement.class)));
                            return new LoadingFunctionSuppliersConfig(suppliers);
                        })
                .registerTypeAdapter(ConfigElement.class,
                        (JsonDeserializer<ConfigElement>) (json, type, context) -> {
                            JsonObject o = json.getAsJsonObject();
                            JsonElement supplierJson = o.get("supplierData");
                            JsonElement functionJson = o.get("functionData");
                            return new ConfigElement(supplierJson, functionJson);
                        })
                .create();
    }
}
/*
{
    "Identifier_of_LoadingFunctionSupplier": {
        "supplierData": "value for LoadingFunctionSupplier (JsonElement)",
        "functionData": "any jsonElement"
    }
    ...
}
 */