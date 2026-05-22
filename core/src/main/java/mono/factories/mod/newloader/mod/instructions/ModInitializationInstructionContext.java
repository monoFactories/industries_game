package mono.factories.mod.newloader.mod.instructions;

import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;

public class ModInitializationInstructionContext {
    private final Registry<Object> context = new DefaultRegistry<>();

    public Registry<Object> getContext() {
        return context;
    }
}
