package mono.factories.mod.newloader.mod.stages;

import mono.factories.dependencies.CheckedHasDependency;
import mono.factories.dependencies.HasDependency;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class InstructionStage implements HasDependency {
    public static final Registry<InstructionStage> instructionStages = new StandardRegistry<>();

    private final CheckedHasDependency dependency;

    public InstructionStage(CheckedHasDependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public Identifier[] dependencies() {
        return dependency.dependencies();
    }

    @Override
    public Identifier id() {
        return dependency.id();
    }
}
