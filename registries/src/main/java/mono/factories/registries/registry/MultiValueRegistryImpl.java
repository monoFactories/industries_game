package mono.factories.registries.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// // Map<Identifier, Map<Identifier, T>>
public class MultiValueRegistryImpl<T> implements MultiValueRegistry<T> {
    private final Object2ObjectMap<Identifier, Registry<T>> map = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());

    @Override
    public Registry<T> get(Identifier id) {
        return map.get(id);
    }

    @Override
    public T get(Identifier reg, Identifier val) {
        Registry<T> regi = map.get(reg);
        if (regi != null) {
            return regi.get(val);
        }
        return null;
    }

    @Override
    public Identifier register(Identifier id, Registry<T> bucket) {
        Registry<T> a = bucket == null ? getRegistryImpl() : bucket;
        map.put(id, a);
        return id;
    }

    @Override
    public Storage2<Identifier, Registry<T>> registerStorage(Identifier id, Registry<T> bucket) {
        Registry<T> a = bucket == null ? getRegistryImpl() : bucket;
        map.put(id, a);
        return new Storage2<>(id, a);
    }

    @Override
    public Registry<T> getRegistryImpl() {
        return new StandardRegistry<>();
    }

    @Override
    public boolean containsBucket(Identifier id) {
        return map.containsKey(id);
    }

    @Override
    public boolean removeBucket(Identifier id) {
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
    public Collection<Registry<T>> values() {
        return map.values();
    }

    @Override
    public Iterator<Registry<T>> iterator() {
        return map.values().iterator();
    }
}
