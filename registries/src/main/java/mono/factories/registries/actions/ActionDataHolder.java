package mono.factories.registries.actions;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;

import java.util.Set;
// List<Identifier>; Map<Identifier, String>
public interface ActionDataHolder<Transmitted> {
    Set<Identifier> actionsID();

    void runAllAction(Transmitted trans);

    void runAction(Identifier id, Transmitted trans);

    Registry<String> getData();
}
