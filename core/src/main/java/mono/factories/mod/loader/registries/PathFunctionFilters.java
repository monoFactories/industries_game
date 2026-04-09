package mono.factories.mod.loader.registries;

import com.google.gson.JsonElement;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

import java.nio.file.Path;
import java.util.function.BiFunction;

public class PathFunctionFilters {
    public static final Registry<BiFunction<Path, JsonElement, Boolean>> filters = new StandardRegistry<>();

    public static final Identifier END_WITH_FILTER = filters.register(new Identifier("end_with_filter"), ((path, element) -> {
        String endWith = element.getAsString();
        return path.endsWith(endWith);
    }));
}
