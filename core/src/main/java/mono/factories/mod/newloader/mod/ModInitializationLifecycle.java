package mono.factories.mod.newloader.mod;

import mono.factories.mod.newloader.mod.instructions.ModInitializationInstruction;
import mono.factories.mod.newloader.mod.instructions.ModInitializationInstructions;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.DefaultHolder;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public abstract class ModInitializationLifecycle {
    public static final DefaultHolder<Supplier<ModInitializationLifecycle>> lifecycleHolder = new DefaultHolder<>(ModInitializationLifecycle::createDefault);

    public abstract void initializeMod(CodeSource codeSource, ModBootstrapConfig config);

    private static ModInitializationLifecycle createDefault() {
        return new DefaultModInitializationLifecycle();
    }

    public static final class DefaultModInitializationLifecycle extends ModInitializationLifecycle {
        @Override
        public void initializeMod(CodeSource codeSource, ModBootstrapConfig config) {
            DefaultModInitializedData data = DefaultModInitializedData.create(config);
            List<Identifier> sortedStages = data.getSortedStages();
            sortedStages.forEach(identifier -> {
                Collection<Identifier> instructions = data.stagesInstructions.get(identifier);
                instructions.forEach(identifier1 -> {
                    ModInitializationInstruction instruction = ModInitializationInstructions.instructions.get(identifier1);
                    if (instruction != null) instruction.instruction(data, codeSource);
                });
            });
        }
    }
}
