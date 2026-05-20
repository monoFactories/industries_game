package mono.factories.mod.newloader.api;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.protection.PolicyBasedRegister;
import org.semver4j.Semver;
import org.semver4j.internal.VersionParser;
import org.semver4j.range.Range;
import org.semver4j.range.RangeList;
import org.semver4j.range.RangeListFactory;

import java.util.Set;

public class LoaderEntryInfo implements Identifiable {
    private static final Set<String> ACCESS = ObjectSets.singleton("mono.factories.mod.newloader.api.LoaderEntryInfo");
    private static final Gson gson;

    private final Identifier id;
    private final Semver version;
    private final Registry<RangeList> dependencies;

    private LoaderEntryInfo(Identifier id, Semver version, Registry<RangeList> dependencies) {
        this.id = id;
        this.version = version;
        this.dependencies = dependencies;
    }

    @Override
    public Identifier id() {
        return id;
    }

    public Semver version() {
        return version;
    }

    public Registry<RangeList> dependencies() {
        return dependencies;
    }

    public LoaderEntryInfo parse(JsonElement json) {
        return gson.fromJson(json, LoaderEntryInfo.class);
    }

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LoaderEntryInfo.class,
                        ((JsonDeserializer<LoaderEntryInfo>) (json, type, context) -> {
                            JsonObject o = json.getAsJsonObject();
                            Identifier id = new Identifier(o.get("id").getAsString());
                            Semver version = Semver.coerce(o.get("version").getAsString());
                            PolicyBasedRegister<RangeList> dependencies = PolicyBasedRegister.getStackTraceBasedRegistry(ACCESS);
                            o.get("dependencies").getAsJsonObject().asMap().forEach((str, je) -> dependencies.register(new Identifier(str), RangeListFactory.create(je.getAsString())));
                            return new LoaderEntryInfo(id, version, dependencies);
                        }))
                .create();
    }
}
