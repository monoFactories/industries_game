package mono.factories.mod.newloader.mod;

import com.google.gson.JsonElement;
import mono.factories.registries.id.Identifier;

import java.util.Map;

public record ModBootstrapConfig(Map<Identifier, JsonElement> variables,
                                 Map<Identifier, Map<Identifier, JsonElement>> stages) {

}
/*
{
    "variables": {
        "var1": "value 1"
    },
    "stages": {
        "initStage": {
            "action": ["value"]
        }
    }
}
 */