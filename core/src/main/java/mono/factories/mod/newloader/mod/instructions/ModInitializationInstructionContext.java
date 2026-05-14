package mono.factories.mod.newloader.mod.instructions;

import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class ModInitializationInstructionContext {
    private final Registry<Object> context = new StandardRegistry<>();

    public Registry<Object> getContext() {
        return context;
    }
}
