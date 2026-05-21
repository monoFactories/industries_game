package mono.factories.mod.newloader.fun;

import com.google.gson.JsonElement;
import mono.factories.mod.newloader.config.LoadingFunctionSuppliersConfig;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.registry.Registry;

public interface LoadingFunctionSupplier {
    void fun(CodeSource source, LoadingFunctionSuppliersConfig.ConfigElement data, LoadingFunctionSuppliersStorage storage, Registry<JsonElement> variables);
}
