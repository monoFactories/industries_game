package mono.factories.registries.actions;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.Storage3;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class DefaultActionDataHolder<TransmittedObject> implements ActionDataHolder<TransmittedObject> {
    private final Set<Identifier> actions;
    private final Registry<String> data;
    private final Consumer<Storage3<Identifier, ActionDataHolder<TransmittedObject>, TransmittedObject>> runActionFun;

    public DefaultActionDataHolder(Set<Identifier> actions, Registry<String> data, Consumer<Storage3<Identifier, ActionDataHolder<TransmittedObject>, TransmittedObject>> runActionFun) {
        if (actions == null || data == null || runActionFun == null)
            throw new NullPointerException("one of the parameters is null");
        this.actions = actions;
        this.data = data;
        this.runActionFun = runActionFun;
    }

    public DefaultActionDataHolder(Consumer<Storage3<Identifier, ActionDataHolder<TransmittedObject>, TransmittedObject>> runActionFun) {
        this(new HashSet<>(), new DefaultRegistry<>(), runActionFun);
    }

    @Override
    public Set<Identifier> actionsID() {
        return actions;
    }

    @Override
    public void runAllAction(TransmittedObject trans) {
        actions.forEach(id -> runAction(id, trans));
    }

    @Override
    public void runAction(Identifier id, TransmittedObject transmittedObject) {
        runActionFun.accept(new Storage3<>(id, this, transmittedObject));
    }

    @Override
    public Registry<String> getData() {
        return data;
    }
}
