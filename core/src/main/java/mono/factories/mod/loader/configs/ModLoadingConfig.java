package mono.factories.mod.loader.configs;

import com.google.gson.*;
import io.github.g00fy2.versioncompare.Version;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.dependencies.HasDependency;
import mono.factories.mod.loader.util.ModVersionRange;
import mono.factories.registries.id.Identifier;

import java.util.Map;

public class ModLoadingConfig implements HasDependency {
    private static final Gson GSON;

    private final String id;
    private final Version version;
    private final Map<String, ModVersionRange> dependencies;

    private ModLoadingConfig(String id, Version version, Map<String, ModVersionRange> dependencies) {
        this.dependencies = dependencies;
        this.id = id;
        this.version = version;
    }

    public static ModLoadingConfig parse(String json) {
        return GSON.fromJson(json, ModLoadingConfig.class);
    }

    static {
        GSON = new GsonBuilder()
                .registerTypeAdapter(ModLoadingConfig.class, (JsonDeserializer<ModLoadingConfig>) (json, typeOfT, context) -> {
                    JsonObject o = json.getAsJsonObject();
                    String id = o.get("id").getAsString();
                    Version version = new Version(o.get("version").getAsString(), true);
                    Object2ObjectMap<String, ModVersionRange> dependencies = new Object2ObjectOpenHashMap<>();
                    o.get("dependencies").getAsJsonObject().asMap().forEach((str, je) -> dependencies.put(str, ModVersionRange.parse(je.getAsString())));
                    return new ModLoadingConfig(id, version, Object2ObjectMaps.unmodifiable(dependencies));
                })
                .create();
    }

    @Override
    public Identifier[] dependencies() {
        Identifier[] depends = new Identifier[dependencies.size()];
        int i = 0;
        for (Map.Entry<String, ModVersionRange> entry : dependencies.entrySet()) {
            depends[i] = new Identifier(entry.getKey());
            i++;
        }
        return depends;
    }

    @Override
    public Identifier id() {
        return new Identifier(id);
    }
}
/*
{
    "name": "tst1",
    "version": "0.0.0-alpha",
    "dependencies": {
        "tst0": ">=0.0.1-alpha"
    }
}
*/