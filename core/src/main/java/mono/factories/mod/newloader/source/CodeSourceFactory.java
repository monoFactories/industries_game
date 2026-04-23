package mono.factories.mod.newloader.source;

import mono.factories.dependencies.DependencyResolver;
import mono.factories.registries.registry.ListRegistry;
import mono.factories.registries.registry.ListRegistryImpl;

public class CodeSourceFactory {
    private final ListRegistry<CodeSourceProvider> providerList = new ListRegistryImpl<>();
    private CodeSourceFactory() {}
    public static CodeSourceFactory create() {
        CodeSourceFactory factory = new CodeSourceFactory();
        // add tag list to providers
    }
}
