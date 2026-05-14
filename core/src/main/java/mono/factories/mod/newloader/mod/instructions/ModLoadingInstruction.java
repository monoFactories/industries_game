package mono.factories.mod.newloader.mod.instructions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import mono.factories.mod.newloader.mod.DefaultModInitializedData;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;

public class ModLoadingInstruction implements ModInitializationInstruction {
    private static final Gson gson;
    public static final Identifier jsonConfigID = new Identifier("mod_loading_config");

    @Override
    public void instruction(DefaultModInitializedData data, CodeSource source) {

    }

    public record LoadingInstructionConfig(Identifier modType, Identifier id, String version, Registry<JsonElement> description, String[] entryPoints, ) {

    }

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LoadingInstructionConfig.class, (JsonDeserializer<LoadingInstructionConfig>) (json, typeOfT, context) -> {

                })
                .create();
    }
    /*
    {
        "type": "library, mod",
        "description": {}
        "version": "version",
        "id": "id",
        "entrypoints": [
        ],
        "dependency": [
        ]
    }
     */
}
