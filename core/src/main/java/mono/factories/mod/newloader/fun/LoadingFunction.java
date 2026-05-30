package mono.factories.mod.newloader.fun;

import com.google.gson.JsonElement;
import mono.factories.mod.newloader.source.provider.CodeSource;

public interface LoadingFunction {
    void fun(CodeSource source, JsonElement functionData);
}
