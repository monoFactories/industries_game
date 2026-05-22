package mono.factories.events;

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
