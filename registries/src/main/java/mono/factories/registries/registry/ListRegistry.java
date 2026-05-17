package mono.factories.registries.registry;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.List;
import java.util.Set;

// Map<Identifier, List<T>>
public interface ListRegistry<T> extends Iterable<List<T>> {
    List<T> get(Identifier id);

    Identifier register(Identifier id, List<T> item);

    Storage2<Identifier, List<T>> registerStorage(Identifier id, List<T> item);

    boolean contains(Identifier id);

    boolean remove(Identifier id);

    int size();

    Set<Identifier> keys();

    List<List<T>> values();
}
