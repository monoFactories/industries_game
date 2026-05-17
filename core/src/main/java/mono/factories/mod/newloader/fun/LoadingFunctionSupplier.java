package mono.factories.mod.newloader.fun;

import com.google.gson.JsonElement;
import mono.factories.mod.newloader.source.provider.CodeSource;

public interface LoadingFunctionSupplier {
    void fun(CodeSource source, LoadingFunctionSuppliersConfig.ConfigElement data, LoadingFunctionSuppliersStorage storage);
}
