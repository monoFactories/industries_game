package mono.factories.mod.newloader.fun;

import mono.factories.mod.newloader.config.LoadingFunctionSuppliersConfig;
import mono.factories.mod.newloader.source.provider.CodeSource;

public interface LoadingFunctionSupplier {
    void fun(CodeSource source, LoadingFunctionSuppliersConfig.ConfigElement data, LoadingFunctionSuppliersStorage storage);
}
