package mono.factories.engines.process;

import mono.factories.dependencies.CheckedHasDependency;
import mono.factories.dependencies.HasDependency;
import mono.factories.engines.engine.Engine;
import mono.factories.registries.id.Identifier;

public abstract class Process implements HasDependency {
    private final CheckedHasDependency hasDependency;

    public Process(Identifier id, Identifier[] dependencies) {
        if (id == null) throw new NullPointerException("id is null");
        this.hasDependency = new CheckedHasDependency(dependencies, id);
    }
    public abstract void run(Engine engine);
    @Override
    public final Identifier id() {
        return hasDependency.id();
    }
    @Override
    public final Identifier[] dependencies() {
        return hasDependency.dependencies();
    }
}
