package mono.factories.registries.registry.protection;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.util.*;
import java.util.function.Supplier;

public class PolicyBasedRegister<T> extends StandardRegistry<T> {
    public static final Supplier<Boolean> allowAll = () -> true;
    private final Supplier<Boolean> checker;

    public PolicyBasedRegister(Supplier<Boolean> checker) {
        this.checker = checker == null ? allowAll : checker;
    }

    @Override
    public Identifier register(Identifier id, T item) {
        if (!checker.get()) {
            throw new UnsupportedOperationException(
                    "Registration denied by policy: operation not permitted under current access rules. " +
                            "Check failed in PolicyBasedRegister.checker."
            );
        }
        return super.register(id, item);
    }

    @Override
    public Storage2<Identifier, T> registerStorage(Identifier id, T item) {
        if (!checker.get()) {
            throw new UnsupportedOperationException(
                    "Storage registration denied by policy: insufficient privileges or context mismatch. " +
                            "Policy check failed in PolicyBasedRegister.checker."
            );
        }
        return super.registerStorage(id, item);
    }

    @Override
    public boolean remove(Identifier id) {
        if (!checker.get()) {
            throw new UnsupportedOperationException(
                    "Item removal denied by policy: action blocked due to access control rules. " +
                            "Checker condition evaluated to false in PolicyBasedRegister."
            );
        }
        return super.remove(id);
    }

    @Override
    public Set<Identifier> keys() {
        return Collections.unmodifiableSet(super.keys());
    }

    @Override
    public Collection<T> values() {
        return Collections.unmodifiableCollection(super.values());
    }

    public static <T> PolicyBasedRegister<T> getStackTraceBasedRegistry(Set<String> allowedClasses) {
        return new PolicyBasedRegister<>(() -> {
            StackTraceElement[] trace = Thread.currentThread().getStackTrace(); // [..., allowedClass.xz(), ]
            if (trace.length > 3) {
                  String className = trace[trace.length - 4].getClassName();
                  return allowedClasses.contains(className);
            }
            return false;
        });
    }
}
