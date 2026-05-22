package mono.factories.mod.registries;

import com.google.gson.JsonElement;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;

public class ModInformationRegistries {
    public static final Registry<Registry<JsonElement>> informationRegistry = new DefaultRegistry<>();
}
