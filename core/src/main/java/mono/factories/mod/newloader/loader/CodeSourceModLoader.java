package mono.factories.mod.newloader.loader;

import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.Storage2;

import java.util.function.Supplier;

public abstract class CodeSourceModLoader {
    public abstract void addCodeSource(CodeSource source);

    public abstract void launch(Registry<Object> context);

    public static final Registry<Supplier<CodeSourceModLoader>> modLoaders = new DefaultRegistry<>();
    public static final Storage2<Identifier, Supplier<CodeSourceModLoader>> Default_MOD_LOADER = modLoaders.registerStorage(new Identifier("Default_mod_loader"), defaultLoader());
    private static Supplier<CodeSourceModLoader> defaultLoader() {
        return DefaultCodeSourceModLoader::create;
    }
}