package mono.factories.interfacefx.nodeparameters;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;

import java.lang.reflect.Type;

public class NodeParametersParser implements JsonDeserializer<NodeParameters>, JsonSerializer<NodeParameters> {
    public static final Gson parserGson;
    /*
    {
        "actions": {
            "id_group": [
                "id0", "id1"
            ]
        }
        "data": {
            "data_id0": "value0"
        }
    }
     */
    @Override
    public NodeParameters deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject parameterObject = json.getAsJsonObject();
        NodeParameterActions npa = context.deserialize(parameterObject.get("actions"), NodeParameterActions.class);
        Object2ObjectOpenHashMap<Identifier, String> data = new Object2ObjectOpenHashMap<>();
        parameterObject.get("data").getAsJsonObject().asMap().forEach((str, je) -> {
            data.put(new Identifier(str), je.getAsString());
        });
        return new NodeParameters(npa, data);
    }

    @Override
    public JsonElement serialize(NodeParameters src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject nodeParameterJson = new JsonObject();
        nodeParameterJson.add("actions", context.serialize(src.getActions(), NodeParameterActions.class));
        JsonObject data = new JsonObject();
        src.getData().forEach((id, str) -> {data.add(id.toString(), new JsonPrimitive(str));});
        nodeParameterJson.add("data", data);
        return null;
    }

    static {
        parserGson = new GsonBuilder()
                .registerTypeAdapter(NodeParameterActionParser.class, new NodeParameterActionParser())
                .registerTypeAdapter(NodeParametersParser.class, new NodeParametersParser())
                .create();
    }
}
