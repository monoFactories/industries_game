package mono.factories.registries;

import java.util.function.Supplier;

public class LazyInitializer <T> {
    private T t;
    private final Supplier<T> initFunction;

    public LazyInitializer(Supplier<T> initFunction) {
        this.initFunction = initFunction;
    }

    public T get() {
        if (t == null)
            t = initFunction.get();
        return t;
    }
}
