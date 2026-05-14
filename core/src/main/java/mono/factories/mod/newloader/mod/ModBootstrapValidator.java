package mono.factories.mod.newloader.mod;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class ModBootstrapValidator {
    public static final String FILE_CONFIG_NAME = "bootstrap.json";
    private static final Gson deserializer;

    public static ModBootstrapConfig readConfig(CodeSource source) throws IOException {
        return deserializer.fromJson(source.readAsString(FILE_CONFIG_NAME), ModBootstrapConfig.class);
    }

    static {
        deserializer = new GsonBuilder()
                .registerTypeAdapter(ModBootstrapConfig.class, (JsonDeserializer<ModBootstrapConfig>) (JsonElement json, Type typeOfT, JsonDeserializationContext context) -> {
                    JsonObject o = json.getAsJsonObject();
                    Map<Identifier, JsonElement> variables = new Object2ObjectOpenHashMap<>();
                    Map<Identifier, Map<Identifier, JsonElement>> stages = new Object2ObjectOpenHashMap<>();
                    o.get("variables").getAsJsonObject().asMap().forEach((str, je) -> variables.put(new Identifier(str), je));
                    o.get("stage").getAsJsonObject().asMap().forEach((stageId, stageJe) -> {
                        Map<Identifier, JsonElement> stage = new Object2ObjectOpenHashMap<>();
                        stageJe.getAsJsonObject().asMap().forEach((idAction, je) -> stage.put(new Identifier(idAction), je));
                        stages.put(new Identifier(stageId), stage);
                    });
                    return new ModBootstrapConfig(variables, stages);
                })
                .create();
    }
}
