package mono.factories.dependencies;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDependencyResolver<T extends HasTag> extends StandardRegistry<T> {
    private final Object processedLock = new Object();
    private volatile boolean needUpdate = false;

    @Override
    public boolean remove(Identifier id) {
        synchronized (processedLock) {
            needUpdate = true;
            return super.remove(id);
        }
    }

    @Override
    public Storage2<Identifier, T> registerStorage(Identifier id, T item) {
        synchronized (processedLock) {
            needUpdate = true;
            return super.registerStorage(id, item);
        }
    }

    @Override
    public Identifier register(Identifier id, T item) {
        synchronized (processedLock) {
            needUpdate = true;
            return super.register(id, item);
        }
    }

    public TagResolverResult<T> resolve() {
        Map<Identifier, List<T>> map = new HashMap<>();
        forEach(hasTag -> {
            for (Identifier dependency : hasTag.dependencies()) {
                map.computeIfAbsent(dependency, k -> new ArrayList<>()).add(hasTag);
            }
        });
        return new TagResolverResult<>(map);
    }
}
