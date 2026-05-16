package mono.factories.mod.newloader.loader;

import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.util.function.Supplier;

public abstract class CodeSourceModLoader {
    public abstract void addCodeSource(CodeSource source);

    public abstract void launch(Registry<Object> context);

    public static final Registry<Supplier<CodeSourceModLoader>> preparatoryModLoaders = new StandardRegistry<>();
    public static final Storage2<Identifier, Supplier<CodeSourceModLoader>> STANDARD_PREPARATORY_LOADER = preparatoryModLoaders.registerStorage(new Identifier("standard_preparatory_loader"), simple());

    private static Supplier<CodeSourceModLoader> simple() {
        return SimpleCodeSourceModLoader::create;
    }
}