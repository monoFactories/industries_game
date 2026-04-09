package mono.factories.mod.loader.providers;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.mod.loader.registries.PathFunctionFilters;
import mono.factories.registries.id.Identifier;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class FilteredFolderProviderConfig {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(FilteredFolderProviderConfig.class, new Deserializer()).create();
    private final String path;
    private final List<FilterEntry> filters;

    public FilteredFolderProviderConfig(String path, List<FilterEntry> filters) {
        this.path = path;
        this.filters = filters;
    }

    public List<FilterEntry> getFilters() {
        return filters;
    }

    public String getPath() {
        return path;
    }

    public static FilteredFolderProviderConfig parse(JsonElement je) {
        return GSON.fromJson(je, FilteredFolderProviderConfig.class);
    }

    private static final class Deserializer implements JsonDeserializer<FilteredFolderProviderConfig> {
        @Override
        public FilteredFolderProviderConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject o = json.getAsJsonObject();
            String path = o.get("path").getAsString();
            ObjectArrayList<FilterEntry> filters = new ObjectArrayList<>();
            o.get("filters").getAsJsonObject().asMap().forEach((str, je) -> {
                BiFunction<Path, JsonElement, Boolean> function = PathFunctionFilters.filters.get(new Identifier(str));
                if (function != null) {
                    filters.add(new FilterEntry(function, je));
                }
            });
            return new FilteredFolderProviderConfig(path, filters);
        }
    }

    public record FilterEntry(BiFunction<Path, JsonElement, Boolean> function, JsonElement element) implements Function<Path, Boolean> {

        @Override
        public Boolean apply(Path path) {
            return function.apply(path, element);
        }
    }
}
