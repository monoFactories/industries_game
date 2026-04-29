package mono.factories.mod.newloader.mod;

import com.google.gson.*;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class ModBootstrapValidator {
    public static final String FILE_CONFIG_NAME = "bootstrap.json";
    private static final Gson deserializer;

    public static ValidationResult validate(CodeSource source) throws IOException {
        source.readAsString(FILE_CONFIG_NAME);
    }

    public record ValidationResult(Registry<JsonElement> variables, ) {

    }

    static {
        deserializer = new GsonBuilder()
                .registerTypeAdapter(ValidationResult.class, (JsonDeserializer<ValidationResult>)(JsonElement json, Type typeOfT, JsonDeserializationContext context) -> {

                    return new ValidationResult();
                })
                .create();
    }
}
