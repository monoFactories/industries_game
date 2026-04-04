package mono.factories.mod.loader;

import com.google.gson.*;
import io.github.g00fy2.versioncompare.Version;
import it.unimi.dsi.fastutil.objects.*;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.protection.PolicyBasedRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.List;

public class ModInfo {
    public static final Logger logger = LogManager.getLogger(ModInfo.class);

    public static final Gson MOD_INFO_GSON;

    private final String id;
    private final Version version;
    private final List<String> entryPoints;
    private final Registry<String> information;
    private final Registry<ModVersionRange> dependencies;

    ModInfo(String id, Version version, List<String> entryPoints, Registry<String> information, Registry<ModVersionRange> dependencies) {
        this.id = id;
        this.version = version;
        this.entryPoints = entryPoints;
        this.information = information;
        this.dependencies = dependencies;
    }

    public static ModInfo parse(String fileContent) {
        return MOD_INFO_GSON.fromJson(fileContent, ModInfo.class);
    }

    public String getId() {
        return id;
    }

    public Version getVersion() {
        return version;
    }

    public List<String> getEntryPoints() {
        return entryPoints;
    }

    public Registry<String> getInformation() {
        return information;
    }

    public Registry<ModVersionRange> getDependencies() {
        return dependencies;
    }

    static final class ModInfoJson implements JsonDeserializer<ModInfo> {
        private static final ReferenceSet<String> ALLOWED_CLASS = ReferenceSets.unmodifiable(new ReferenceArraySet<>(List.of("mono.factories.mod.loader.ModInfo$ModInfoJson")));
        public static final ModInfoJson JSON = new ModInfoJson();
        @Override
        public ModInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject modInfoObject = json.getAsJsonObject();
            String id = modInfoObject.get("id").getAsString();
            Version version = new Version(modInfoObject.get("version").getAsString(), true);
            ObjectList<String> entryPoints = new ObjectArrayList<>();
            JsonArray entryPointsJson = modInfoObject.get("entrypoints").getAsJsonArray();
            entryPointsJson.forEach(entryPoint -> {
                entryPoints.add(entryPoint.getAsString());
            });
            List<String> entryPointsUnmodifiable = ObjectLists.unmodifiable(entryPoints);
            Registry<String> informationRegistry = PolicyBasedRegister.getStackTraceBasedRegistry(ALLOWED_CLASS);
            JsonObject information = modInfoObject.get("information").getAsJsonObject();
            information.asMap().forEach((str, je) -> {
                try {
                    Identifier infoEntryId = new Identifier(str);
                    String value = je.getAsString();
                    informationRegistry.register(infoEntryId, value);
                } catch (Exception e) {
                    logger.warn("Exception when registering key-value '{}' in information of mod (ID: {})", str, id, e);
                }
            });
            Registry<ModVersionRange> dependenciesRegistry = PolicyBasedRegister.getStackTraceBasedRegistry(ALLOWED_CLASS);
            JsonObject dependencies = modInfoObject.get("dependencies").getAsJsonObject();
            dependencies.asMap().forEach((str, je) -> {
                /// !!! add logging if range is not valid !!! (after ... 10000000000 years:, before 1 bug))
                Identifier dependEntryId = new Identifier(str);
                ModVersionRange range = ModVersionRange.parse(je.getAsString());
                dependenciesRegistry.register(dependEntryId, range);
            });
            return new ModInfo(id, version, entryPointsUnmodifiable, informationRegistry, dependenciesRegistry);
        }
    }
    static {
        MOD_INFO_GSON = new GsonBuilder()
                .registerTypeAdapter(Identifier.class, Identifier.JSON)
                .registerTypeAdapter(ModInfo.class, ModInfoJson.JSON)
                .create();
    }
}
