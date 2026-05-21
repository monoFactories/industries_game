package mono.factories.mod.newloader.config;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.util.ArrayList;
import java.util.List;

public class LoadingFunctionSuppliersConfig {
    public static final Gson g;
    public final List<Storage2<Identifier, ConfigElement>> suppliers;

    public LoadingFunctionSuppliersConfig(List<Storage2<Identifier, ConfigElement>> suppliers) {
        this.suppliers = suppliers;
    }

    public static LoadingFunctionSuppliersConfig create(JsonElement json) {
        return g.fromJson(json, LoadingFunctionSuppliersConfig.class);
    }

    public record ConfigElement(JsonElement supplierData, JsonElement functionData) {

    }

    static {
        g = new GsonBuilder()
                .registerTypeAdapter(LoadingFunctionSuppliersConfig.class,
                        (JsonDeserializer<LoadingFunctionSuppliersConfig>) (json, typeOfT, context) -> {
                            JsonObject o = json.getAsJsonObject();
                            List<Storage2<Identifier, ConfigElement>> suppliers = new ObjectArrayList<>();
                            o.asMap().forEach((str, val) -> suppliers.add(new Storage2<>(new Identifier(str), context.deserialize(val, ConfigElement.class))));
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
[
    "Identifier_of_LoadingFunctionSupplier": {
        "supplierData": "value for LoadingFunctionSupplier (JsonElement)",
        "functionData": "any jsonElement"
    }
    ...
]
 */