package mono.factories.mod.loader.configs;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.protection.PolicyBasedRegister;
import mono.factories.registries.storage.Storage2;

import java.util.List;
import java.util.Set;

public class StandardModLoadConfig {
    public static final String MOD_LOAD_CONFIG_FILENAME = "mod_loader_parameters.json";
    private static final Set<String> access = ObjectSets.singleton("mono.factories.mod.loader.configs.StandardModLoadConfig");
    private static final Gson gson;

    private final Registry<JsonElement> information;
    private final List<String> entryPoints;
    private final List<Storage2<Identifier, JsonElement>> handlers;

    private StandardModLoadConfig(List<String> entryPoints, Registry<JsonElement> information, List<Storage2<Identifier, JsonElement>> handlers) {
        this.entryPoints = entryPoints;
        this.information = information;
        this.handlers = handlers;
    }

    public List<String> getEntryPoints() {
        return entryPoints;
    }

    public List<Storage2<Identifier, JsonElement>> getHandlers() {
        return handlers;
    }

    public Registry<JsonElement> getInformation() {
        return information;
    }

    public static StandardModLoadConfig parse(String json) {
        return gson.fromJson(json, StandardModLoadConfig.class);
    }

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(StandardModLoadConfig.class,
                        (JsonDeserializer<StandardModLoadConfig>) (json, typeOfT, context) -> {
                            JsonObject o = json.getAsJsonObject();
                            Registry<JsonElement> information = PolicyBasedRegister.getStackTraceBasedRegistry(access);
                            List<String> entryPoints = new ObjectArrayList<>();
                            List<Storage2<Identifier, JsonElement>> handlers = new ObjectArrayList<>();
                            o.get("information").getAsJsonObject().asMap().forEach((idStr, je) -> information.register(new Identifier(idStr), je));
                            o.get("entryPoints").getAsJsonArray().forEach(je -> entryPoints.add(je.getAsString()));
                            o.get("handlers").getAsJsonObject().asMap().forEach((str, je) -> handlers.add(new Storage2<>(new Identifier(str), je)));
                            return new StandardModLoadConfig(entryPoints, information, handlers);
                        })
                .create();
    }
}
/*
{
    "information": {
        "name": "TEST MOD",
        "authors": [
            "First Author",
            "Second Author",
            "Third Author"
        ]
    }
    "entryPoints": [
        "mono.factories.mod.loader.configs.StandardModLoadConfig",
        "mono.factories.mod.Mod"
    ],
    "handlers": {
        "called": {
            "called_handler_id": ["data"]
        }
    }
}
 */