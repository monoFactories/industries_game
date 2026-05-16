package mono.factories.mod.newloader.loader;

import mono.factories.mod.newloader.mod.DefaultModInitializedData;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

public abstract class CodeSourceInitializer {
    public abstract void init(CodeSource source, DefaultModInitializedData data);

    public static final Registry<CodeSourceInitializer> initializers = new StandardRegistry<>();
    public static final Storage2<Identifier, CodeSourceInitializer> STANDARD_INITIALIZER;

    static {
        STANDARD_INITIALIZER = initializers.registerStorage(new Identifier("standard_initializer"), new CodeSourceInitializer() {
            @Override
            public void init(CodeSource source, DefaultModInitializedData data) {

            }
        });
    }
}
