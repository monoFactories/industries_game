package mono.factories.registries.registry;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;

import java.util.Collection;
import java.util.Set;

// // Map<Identifier, Map<Identifier, T>>
public interface MultiValueRegistry<T> extends Iterable<Registry<T>> {
    Registry<T> get(Identifier id);

    T get(Identifier reg, Identifier val);

    Identifier register(Identifier id, Registry<T> bucket);

    Storage2<Identifier, Registry<T>> registerStorage(Identifier id, Registry<T> bucket);

    Registry<T> getRegistryImpl();

    boolean containsBucket(Identifier id);

    boolean removeBucket(Identifier id);

    int size();

    Set<Identifier> keys();

    Collection<Registry<T>> values();
}
