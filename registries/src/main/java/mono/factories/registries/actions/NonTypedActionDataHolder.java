package mono.factories.registries.actions;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.Storage3;

import java.util.Set;
import java.util.function.Consumer;

public class NonTypedActionDataHolder extends StandardActionDataHolder<Object> {
    public NonTypedActionDataHolder(Set<Identifier> actions, Registry<String> data, Consumer<Storage3<Identifier, ActionDataHolder<Object>, Object>> runActionFun) {
        super(actions, data, runActionFun);
    }

    public NonTypedActionDataHolder(Consumer<Storage3<Identifier, ActionDataHolder<Object>, Object>> runActionFun) {
        super(runActionFun);
    }
}
