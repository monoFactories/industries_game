package mono.factories.registries.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.*;

// Map<Identifier, List<T>>
public class ListRegistryImpl <T> implements ListRegistry<T> {
    private final Map<Identifier, Collection<T>> map = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());

    @Override
    public Collection<T> get(Identifier id) {
        return map.get(id);
    }

    @Override
    public Identifier register(Identifier id, Collection<T> item) {
        map.put(id, item);
        return id;
    }

    @Override
    public Storage2<Identifier, Collection<T>> registerStorage(Identifier id, Collection<T> item) {
        map.put(id, item);
        return new Storage2<>(id, item);
    }

    @Override
    public boolean contains(Identifier id) {
        return map.containsKey(id);
    }

    @Override
    public boolean remove(Identifier id) {
        return map.remove(id) != null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Set<Identifier> keys() {
        return map.keySet();
    }

    @Override
    public Collection<Collection<T>> values() {
        return map.values();
    }

    @Override
    public Iterator<Collection<T>> iterator() {
        return map.values().iterator();
    }
}
