package mono.factories.mod.registries;

import com.google.gson.JsonElement;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;

public class ModInformationRegistries {
    public static final Registry<Registry<JsonElement>> informationRegistry = new StandardRegistry<>();
}
