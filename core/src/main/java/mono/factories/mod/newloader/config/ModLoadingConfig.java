package mono.factories.mod.newloader.config;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import mono.factories.registries.registry.Registry;

import java.util.Set;

public class ModLoadingConfig {
    private static final Set<String> ACCESS = ObjectSets.singleton("mono.factories.mod.newloader.config.ModLoadingConfig");
    private final Registry<JsonElement> variables;
    private final
}
