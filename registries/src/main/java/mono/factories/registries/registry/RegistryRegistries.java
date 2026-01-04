package mono.factories.registries.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;

import java.util.concurrent.ConcurrentMap;

public class RegistryRegistries {
    public static final Registry<Registry<?>> root = new StandardRegistry<>();

    public static <T> Registry<T> register(Registry<T> registry, Identifier id) {
        root.register(id, registry);
        return registry;
    }
}
