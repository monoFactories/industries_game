package mono.factories.mod.newloader.dml;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.RegistryRegistries;
import mono.factories.registries.storage.DefaultHolder;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DMLoader implements ModLoader {
    public static final DefaultHolder<Function<Registry<DependencyEntry>, List<Exception>>> dependencyChecker;
    public static final DefaultHolder<BiConsumer<CodeSource, ModLoaderConfig>> classLoadingConsumer;

    public void load(CodeSource source, ModLoaderConfig config) {
        ModID id = config.id();
        List<Exception> exceptions = dependencyChecker.get().apply(config.dependencies());
        if (!exceptions.isEmpty()) {
            // logging
            return;
        }
        classLoadingConsumer.get().accept(source, config);
    }

    static {
        dependencyChecker = new DefaultHolder<>(registry -> {
            List<Exception> exceptions = new ObjectArrayList<>();
            registry.forEach((modID, dependencyEntry) -> {
                Identifier registryPath = dependencyEntry.path();
                Registry<ModEntry> registryWithDependency = ModRegisters.modRegisters.get(registryPath);
                if (registryWithDependency == null) {
                    exceptions.add(new IllegalArgumentException("Registry not found for path: " + registryPath));
                } else {
                    if (!registryWithDependency.contains(modID)) {
                        exceptions.add(new IllegalArgumentException("Mod ID not found in registry: " + modID));
                    }
                }
            });
            return exceptions;
        });
        classLoadingConsumer = new DefaultHolder<>((codesource, config) -> {

        });
    }

}
