package mono.factories.registries.registry;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.Collection;
import java.util.Set;

// Map<Identifier, List<T>>
public interface ListRegistry<T> extends Iterable<Collection<T>> {
    Collection<T> get(Identifier id);

    Identifier register(Identifier id, Collection<T> item);

    Storage2<Identifier, Collection<T>> registerStorage(Identifier id, Collection<T> item);

    boolean contains(Identifier id);

    boolean remove(Identifier id);

    int size();

    Set<Identifier> keys();

    Collection<Collection<T>> values();
}
