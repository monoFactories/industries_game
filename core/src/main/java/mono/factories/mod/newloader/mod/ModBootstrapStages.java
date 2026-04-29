package mono.factories.mod.newloader.mod;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import mono.factories.registries.id.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModBootstrapStages {
    public record NewStageConfig(Identifier stageId, Identifier[] dependencies, int priority) {

    }
    static JsonDeserializer<NewStageConfig> getNewStagesDeserializer() {
        return ((json, typeOfT, context) -> {
            JsonObject o = json.getAsJsonObject();
            Identifier id = new Identifier(o.get("id").getAsString());
            List<Identifier> dependencies = new ArrayList<>();
            o.get("dependencies").getAsJsonArray().forEach(dependencyElement -> {
                dependencies.add(new Identifier(dependencyElement.getAsString()));
            });
            int priority = o.get("priority").getAsInt();
            return new NewStageConfig(id, dependencies.toArray(Identifier.EMPTY_ARRAY), priority);
        });
    }
}
