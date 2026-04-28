package mono.factories.mod.newloader.source;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.mod.newloader.source.provider.CodeSourceProvider;
import mono.factories.mod.newloader.source.provider.CodeSourceProviders;
import mono.factories.registries.storage.DefaultHolder;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Supplier;

public abstract class CodeSourceFactory {
    public static final DefaultHolder<Supplier<CodeSourceFactory>> factoryHolder = new DefaultHolder<>(CodeSourceFactory::dummyGetSimple);

    public abstract CodeSourceProvider chooseProvider(Path path);

    private static CodeSourceFactory dummyGetSimple() {
        return SimpleCodeSourceFactory.create();
    }

    public static final class SimpleCodeSourceFactory extends CodeSourceFactory {
        private final List<CodeSourceProvider> providersList;

        private SimpleCodeSourceFactory(List<CodeSourceProvider> providersList) {
            this.providersList = providersList;
        }

        @Override
        public CodeSourceProvider chooseProvider(Path path) {
            for (CodeSourceProvider codeSourceProvider : providersList) {
                boolean supports = codeSourceProvider.supports(path);
                if (supports) {
                    return codeSourceProvider;
                }
            }
            return null;
        }

        public static SimpleCodeSourceFactory create() {
            List<CodeSourceProvider> providers = new ObjectArrayList<>();
            CodeSourceProviders.CODE_SOURCE_PROVIDERS.forEach(codeSourceProvider -> providers.add(codeSourceProvider));
            return new SimpleCodeSourceFactory(providers);
        }
    }
}
