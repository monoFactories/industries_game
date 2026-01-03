package mono.factories.registries.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;

import java.util.*;
import java.util.function.Consumer;

public class StandardRegistry<T> implements Registry<T> {
    private final Object2ObjectMap<Identifier, T> map = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());

    @Override
    public T get(Identifier id) {
        return map.get(id);
    }

    @Override
    public void register(Identifier id, T item) throws IllegalArgumentException {
        map.put(id, item);
    }

    @Override
    public boolean contains(Identifier id) {
        return map.containsKey(id);
    }

    @Override
    public boolean remove(Identifier id) {
        return map.containsKey(id);
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
    public Collection<T> values() {
        return map.values();
    }

    @Override
    public Optional<T> getOptional(Identifier id) {
        return Optional.ofNullable(get(id));
    }

    @Override
    public Iterator<T> iterator() {
        return map.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Registry.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Registry.super.spliterator();
    }
}
