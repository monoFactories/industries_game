package mono.factories.interfacefx.nodeparameters;

import com.google.gson.*;
import mono.factories.registries.id.Identifier;

import java.lang.reflect.Type;
import java.util.Map;

public class NodeParameterActionParser implements JsonSerializer<NodeParameterActions>, JsonDeserializer<NodeParameterActions> {
    /*
    "action": {
        "groupId0": [
            "id0", "id1"
        ]
    }
     */
    @Override
    public NodeParameterActions deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject actionObject = json.getAsJsonObject();
        NodeParameterActions result = new NodeParameterActions();
        for (Map.Entry<String, JsonElement> entry : actionObject.entrySet()) {
            Identifier id = new Identifier(entry.getKey());
            JsonArray idArray = entry.getValue().getAsJsonArray();
            for (JsonElement element : idArray) {
                Identifier valueId = new Identifier(element.getAsString());
                result.add(id, valueId);
            }
        }
        return result;
    }

    @Override
    public JsonElement serialize(NodeParameterActions src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject action = new JsonObject();
        src.forEachType((id, idList) -> {
            JsonArray idArray = new JsonArray();
            idList.forEach(valueId -> idArray.add(valueId.toString()));
            action.add(id.toString(), idArray);
        });
        return action;
    }
}
