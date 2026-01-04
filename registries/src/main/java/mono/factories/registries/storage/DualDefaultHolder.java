package mono.factories.registries.storage;

public class DualDefaultHolder <A> {
    private DefaultHolder<A> one, two;

    public DualDefaultHolder(A val0, A val1) {
        one = new DefaultHolder<>(val0);
        two = new DefaultHolder<>(val1);
    }
    public A get1() {
        return one.get();
    }

    public A get2() {
        return two.get();
    }

    public A get1Constant() {
        return one.constant();
    }

    public A get2Constant() {
        return two.constant();
    }
}
