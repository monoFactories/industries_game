package mono.factories.events;

import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

public class Event<T> {
    private final T t;

    public Event(T t) {
        this.t = t;
    }

    public Event() {
        this.t = null;
    }

    public T getTransmittedValue() {
        return t;
    }
}
