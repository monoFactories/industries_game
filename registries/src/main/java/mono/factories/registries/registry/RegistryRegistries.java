package mono.factories.registries.registry;

import mono.factories.registries.id.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistryRegistries {
    public static final Logger logger = LogManager.getLogger(RegistryRegistries.class);

    public static final Registry<Registry<?>> root = new StandardRegistry<>();

    public static <T> Registry<T> register(Registry<T> registry, Identifier id) {
        try {
            if (registry == null) {
                throw new NullPointerException("Attempted to register null registry with ID: " + id);
            }
            if (id == null) {
                throw new NullPointerException("Attempted to register registry with null ID");
            }
            root.register(id, registry);
            logger.info("Successfully registered registry '{}' with ID '{}'", registry.getClass().getSimpleName(), id);
            return registry;
        } catch (Exception e) {
            logger.error("Failed to register registry '{}' with ID '{}': {}", registry.getClass().getSimpleName(), id, e.getMessage());
            throw e;
        }
    }
}
