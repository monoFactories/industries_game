package mono.factories.mod.newloader.api;

import mono.factories.mod.newloader.config.ModLoadDescriptor;
import mono.factories.mod.newloader.source.provider.CodeSource;

import java.io.IOException;

public class ModLoadDescriptorLoader {
    public static final String MOD_LOAD_DESCRIPTOR_FILENAME = "loader_descriptor.json";

    public static ModLoadDescriptor load(CodeSource source) throws IOException {
        String json = source.readAsString(MOD_LOAD_DESCRIPTOR_FILENAME);
        return ModLoadDescriptor.parse(json);
    }
}
