package mono.factories.mod.newloader.fun;

import mono.factories.mod.newloader.dml.ModLoadFunction;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;


public class LoadingFunctions {
    public static final Registry<LoadingFunction> loadingFunctions = new DefaultRegistry<>();

    public static final LoadingFunction MOD_FUNCTION = new ModLoadFunction();
}
