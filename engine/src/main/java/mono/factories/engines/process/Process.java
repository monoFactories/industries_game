package mono.factories.engines.process;

import mono.factories.dependencies.HasDependency;
import mono.factories.engines.engine.Engine;
import mono.factories.registries.id.Identifier;

public abstract class Process implements HasDependency {
    private final Identifier id;
    private final Identifier[] dependencies;

    public Process(Identifier id, Identifier[] dependencies) {
        if (id == null) throw new NullPointerException("id is null");
        this.id = id;
        this.dependencies = dependencies == null ? new Identifier[0] : dependencies;
    }

    @Override
    public final Identifier id() {
        return id;
    }

    public abstract void run(Engine engine);

    public final Identifier[] dependencies() {
        return dependencies;
    }
}
