package mono.factories.mod.newloader.source;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.dependencies.TagDependencyResolver;
import mono.factories.dependencies.TagResolverResult;
import mono.factories.mod.newloader.source.provider.CodeSourceProvider;
import mono.factories.mod.newloader.source.provider.CodeSourceProviders;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class CodeSourceFactory {
    private final TagResolverResult<CodeSourceProvider> result;
    private CodeSourceFactory(TagResolverResult<CodeSourceProvider> result) {
        this.result = result;
    }

    public CodeSourceProvider chooseProvider(Path path) {
        AtomicReference<Consumer<ProviderTagPath>> recursiveIteratorReference = new AtomicReference<>();
        Consumer<ProviderTagPath> recursiveIterator = tagPath -> {
            int l = tagPath.getPath().length;
            List<CodeSourceProvider> providers = new ObjectArrayList<>();
            if (l == 0) {
                providers.addAll(result.getDependencyToChildren().get(TagDependencyResolver.EMPTY_DEPENDENCY));
            } else {
                tagPath.getPath()
            }
        };
        recursiveIteratorReference.set(recursiveIterator);
    }

    public static CodeSourceFactory create() {
        TagDependencyResolver<CodeSourceProvider> resolver = new TagDependencyResolver<>();
        CodeSourceProviders.CODE_SOURCE_PROVIDERS.forEach(resolver::register);
        return new CodeSourceFactory(resolver.resolve());
    }
}
