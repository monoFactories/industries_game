package mono.factories.dependencies;

import mono.factories.registries.id.Identifier;

import java.util.Arrays;
import java.util.Objects;

public final class CheckedHasDependency implements HasDependency {
    private final Identifier identifier;
    private final Identifier[] dependencies;

    public CheckedHasDependency(Identifier[] dependencies, Identifier identifier) {
        Objects.requireNonNull(dependencies, "dependencies array is null object");
        Objects.requireNonNull(identifier, "identifier of CodeSourceProviderBase is null object");
        this.identifier = identifier;
        this.dependencies = dependencies;
    }

    @Override
    public Identifier id() {
        return identifier;
    }

    @Override
    public Identifier[] dependencies() {
        return Arrays.copyOf(dependencies, dependencies.length);
    }

    private static Identifier[] checkDependencies(Identifier[] dependencies, Identifier identifier) {
        return Arrays.stream(dependencies)
                .filter(id -> !id.equals(identifier))
                .distinct()
                .toArray(Identifier[]::new);
    }
}
