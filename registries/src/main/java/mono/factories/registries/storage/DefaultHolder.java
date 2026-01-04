package mono.factories.registries.storage;

public class DefaultHolder <T> {
    private final T constant;
    private  T current;

    public DefaultHolder (T initialValue) {
        if (initialValue == null) throw new NullPointerException("initial value is null");
        constant = initialValue;
    }

    public T get() {
        return current == null ? constant : current;
    }

    public T constant() {
        return constant;
    }
}
