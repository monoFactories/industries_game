package mono.factories.registries.storage;

public class DefaultHolder<T> {
    private final T constant;
    private T current;

    public DefaultHolder(T initialValue) {
        if (initialValue == null) throw new NullPointerException("initial value is null");
        constant = initialValue;
    }

    public T get() {
        return current == null ? constant : current;
    }
    public T getOrDefault(T t) {
        if (t == null)
            return get();
        return t;
    }
    public void set(T t) {
        current = t;
    }

    public T constant() {
        return constant;
    }
}
