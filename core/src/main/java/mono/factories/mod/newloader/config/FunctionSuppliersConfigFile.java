package mono.factories.mod.newloader.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mono.factories.mod.newloader.source.provider.CodeSource;

import java.io.IOException;

public class FunctionSuppliersConfigFile {
    public static final String SUPPLIER_FUNCTIONS_FILENAME = "loading_function.json";
    public static final Gson emptyGson = new Gson();

    public static LoadingFunctionSuppliersConfig read(CodeSource source) throws IOException {
        String json = source.readAsString(SUPPLIER_FUNCTIONS_FILENAME);
        JsonObject o = emptyGson.fromJson(json, JsonObject.class);
        return LoadingFunctionSuppliersConfig.create(o.get("suppliers"));
    }
}
/*
{
    "suppliers": [
        ...
    ]
}
 */