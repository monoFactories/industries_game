package mono.factories.dependencies;

import it.unimi.dsi.fastutil.objects.*;
import mono.factories.registries.id.Identifier;

import java.util.List;
import java.util.Map;

public class TagResolverResult<T extends HasTag> {
    private final Object2ObjectMap<Identifier, ObjectList<T>> dependencyToChildren;

    public TagResolverResult(Map<Identifier, List<T>> dependencyToChildren) {
        Object2ObjectMap<Identifier, ObjectList<T>> map = new Object2ObjectOpenHashMap<>();
        dependencyToChildren.forEach((id, list) -> {
                    map.put(id, new ObjectImmutableList<>(list));
                }
            );
        this.dependencyToChildren = Object2ObjectMaps.unmodifiable(map);
    }

    public Object2ObjectMap<Identifier, ObjectList<T>> getDependencyToChildren() {
        return dependencyToChildren;
    }
}
