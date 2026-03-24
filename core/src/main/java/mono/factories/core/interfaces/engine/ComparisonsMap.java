package mono.factories.core.interfaces.engine;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.ListRegistryImpl;

class ComparisonsMap {
    final Object2ObjectMap<Identifier, Identifier> childToParent = new Object2ObjectOpenHashMap<>();
    final ListRegistryImpl<Identifier> parentToChildren = new ListRegistryImpl<>();
}
