package mono.factories.mod.newloader.mod.instructions;

import mono.factories.mod.newloader.mod.DefaultModInitializedData;
import mono.factories.mod.newloader.source.provider.CodeSource;

public interface ModInitializationInstruction {
    void instruction(DefaultModInitializedData data, ModInitializationInstructionContext context, CodeSource source);
}
