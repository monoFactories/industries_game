package mono.factories.core.interfaces.engine;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.ListRegistryImpl;

import java.util.List;

class ComparisonsMap {
    final Object2ObjectMap<Identifier, Identifier> childToParent = new Object2ObjectOpenHashMap<>();
    final ListRegistryImpl<Identifier> parentToChildren = new ListRegistryImpl<>();
    Identifier rootComponent;

    void addParentToChildrenEntry(UIEngine ui, Identifier parent, Identifier current) {
        List<Identifier> List = ui.comparisons.parentToChildren.get(parent);
        if (List == null) {
            List = ListRegistryImpl.getListInstance();
            ui.comparisons.parentToChildren.register(parent, List);
        }
        List.add(current);
    }
}
