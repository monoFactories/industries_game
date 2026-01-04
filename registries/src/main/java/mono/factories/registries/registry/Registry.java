package mono.factories.registries.registry;

import mono.factories.registries.id.Identifier;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface Registry<T> extends Iterable<T> {
    T get(Identifier id);
    Identifier register(Identifier id, T item);
    boolean contains(Identifier id);
    boolean remove(Identifier id);
    int size();
    Set<Identifier> keys();
    Collection<T> values();
    Optional<T> getOptional(Identifier id);
}
