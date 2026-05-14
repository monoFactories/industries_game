package mono.factories.mod.newloader.mod;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.dependencies.DependencyResolver;
import mono.factories.mod.newloader.mod.instructions.ModInitializationInstruction;
import mono.factories.mod.newloader.mod.instructions.ModInitializationInstructions;
import mono.factories.mod.newloader.mod.stages.InstructionStage;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.ListRegistry;
import mono.factories.registries.registry.ListRegistryImpl;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

import java.util.List;

public class DefaultModInitializedData {
    public final Registry<JsonElement> variables;
    public final ListRegistry<Identifier> stagesInstructions;

    private DefaultModInitializedData(ListRegistry<Identifier> stagesInstructions, Registry<JsonElement> variables) {
        this.stagesInstructions = stagesInstructions;
        this.variables = variables;
    }

    public List<Identifier> getSortedStages() {
        DependencyResolver<InstructionStage> stages = new DependencyResolver<>();
        stagesInstructions.keys().forEach(identifier -> {
            InstructionStage stage = InstructionStage.instructionStages.get(identifier);
            if (stage != null) {
                stages.register(identifier, stage);
            }
        });
        return stages.resolve().stream().map(InstructionStage::id).toList();
    }

    public static DefaultModInitializedData create(ModBootstrapConfig config) {
        Registry<JsonElement> variables = new StandardRegistry<>();
        ListRegistry<Identifier> stagesInstructions = new ListRegistryImpl<>();
        config.stages().forEach((stageIdentifier, identifierJsonElementMap) -> identifierJsonElementMap.forEach((identifier, element) -> {
            if (!stagesInstructions.contains(stageIdentifier)) {
                stagesInstructions.register(stageIdentifier, new ObjectArrayList<>());
            }
            ModInitializationInstruction instruction = ModInitializationInstructions.instructions.get(identifier);
            if (instruction != null)
                stagesInstructions.get(identifier).add(identifier);
        }));
        config.variables().forEach(variables::register);
        return new DefaultModInitializedData(stagesInstructions, variables);
    }
}
