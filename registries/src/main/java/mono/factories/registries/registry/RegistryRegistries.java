package mono.factories.registries.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;

import java.util.concurrent.ConcurrentMap;

public class RegistryRegistries {
    public static final Object2ObjectMap<Identifier, Registry<?>> root = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
}
