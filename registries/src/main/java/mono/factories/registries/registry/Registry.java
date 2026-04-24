package mono.factories.registries.registry;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

// Map<Identifier, T>
public interface Registry<T> extends Iterable<T> {
    T get(Identifier id);

    Identifier register(Identifier id, T item);

    Storage2<Identifier, T> registerStorage(Identifier id, T item);

    boolean contains(Identifier id);

    boolean remove(Identifier id);

    int size();

    Set<Identifier> keys();

    Collection<T> values();

    Optional<T> getOptional(Identifier id);

    void forEach(BiConsumer<Identifier, T> action);
}
