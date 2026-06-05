package mono.factories.mod.newloader.dml;

import com.google.gson.*;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import org.semver4j.range.RangeList;
import org.semver4j.range.RangeListFactory;

public record DependencyEntry(Identifier path, RangeList rangeVersion) {
    private static final Gson gson;

    public static DependencyEntry create(JsonElement e) {
        return gson.fromJson(e, DependencyEntry.class);
    }

    public static Registry<DependencyEntry> createMap(JsonElement e) {
        if (e == null || e.isJsonNull()) return new DefaultRegistry<>();
        JsonObject o = e.getAsJsonObject();
        Registry<DependencyEntry> registry = new DefaultRegistry<>();
        o.asMap().forEach((str, je) -> registry.register(new Identifier(str), create(je)));
        return registry;
    }

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(DependencyEntry.class, (JsonDeserializer<DependencyEntry>) (json, type, context) -> {
                    JsonObject o = json.getAsJsonObject();
                    Identifier path = DMLConstants.DEFAULT_MOD_PATH;
                    JsonElement e = o.get("path");
                    if (e != null && !e.isJsonNull()) {
                        path = new Identifier(e.getAsString());
                    }
                    RangeList version = RangeListFactory.create(o.get("version").getAsString());
                    return new DependencyEntry(path, version);
                })
                .create();
    }
}
/*
    "dependencyModOrLib": {
        "path": "mod",
        "version": ">=1.0.0"
    }
 */