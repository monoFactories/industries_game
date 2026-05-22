package mono.factories.mod.newloader.loader;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.dependencies.DependencyResolver;
import mono.factories.dependencies.HasDependency;
import mono.factories.mod.newloader.api.ModLoadDescriptorLoader;
import mono.factories.mod.newloader.config.ModLoadDescriptor;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.DefaultRegistry;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.storage.DefaultHolder;
import mono.factories.registries.storage.Storage2;
import org.semver4j.range.RangeList;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class DefaultCodeSourceModLoader extends CodeSourceModLoader {
    public static final DefaultHolder<Function<Storage2<List<CodeSource>, Registry<Object>>, Registry<LoadEntry>>> MOD_LOAD_REGISTRY_PROCESSOR;
    public static final DefaultHolder<Consumer<LoadEntry>> loadModConsumer;

    private final List<CodeSource> codeSources;

    private DefaultCodeSourceModLoader(List<CodeSource> codeSources) {
        this.codeSources = codeSources;
    }

    static DefaultCodeSourceModLoader create() {
        return new DefaultCodeSourceModLoader(new ObjectArrayList<>());
    }

    @Override
    public void addCodeSource(CodeSource source) {
        codeSources.add(source);
    }

    @Override
    public synchronized void launch(Registry<Object> context) {
        Registry<LoadEntry> loadEntries = MOD_LOAD_REGISTRY_PROCESSOR.get().apply(new Storage2<>(codeSources, context));
        if (loadEntries != null) {
            DependencyResolver<LoadEntry> resolver = new DependencyResolver<>();
            loadEntries.forEach(loadEntry -> resolver.register(loadEntry.id(), loadEntry));
            List<LoadEntry> mods = resolver.resolve();
        }
    }

    public record LoadEntry(ModLoadDescriptor descriptor, CodeSource source) implements HasDependency {

        @Override
        public Identifier id() {
            return descriptor.id();
        }

        @Override
        public Identifier[] dependencies() {
            Registry<RangeList> dependencies = descriptor.dependencies();
            List<Identifier> dependenciesList = new ObjectArrayList<>();
            dependencies.forEach(((identifier, rangeList) -> dependenciesList.add(identifier)));
            return dependenciesList.toArray(Identifier.EMPTY_ARRAY);
        }
    }

    static {
        MOD_LOAD_REGISTRY_PROCESSOR = new DefaultHolder<>(
                store2 -> {
                    List<CodeSource> codeSources = store2.a();
                    Registry<Object> context = store2.b();
                    Registry<LoadEntry> loadEntries = new DefaultRegistry<>();
                    for (CodeSource source : codeSources) {
                        try {
                            ModLoadDescriptor loadDescriptor = ModLoadDescriptorLoader.load(source);
                            Identifier entryID = loadDescriptor.id();
                            if (loadEntries.contains(entryID)) throw new RuntimeException();
                            loadEntries.register(entryID, new LoadEntry(loadDescriptor, source));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // check dependencies versions
                    boolean[] dependenciesCheckResult = {true};
                    for (LoadEntry entry : loadEntries) {
                        entry.descriptor.dependencies().forEach((dependency, range) -> {
                            LoadEntry dependencyEntry = loadEntries.get(dependency);
                            if (dependencyEntry != null) {
                                if (!range.isSatisfiedBy(dependencyEntry.descriptor.version())) {
                                    //log
                                    dependenciesCheckResult[0] = false;
                                }
                            } else {
                                //log
                                dependenciesCheckResult[0] = false;
                            }
                        });
                    }
                    return (dependenciesCheckResult[0]) ? loadEntries : null;
                }
        );
        loadModConsumer = new DefaultHolder<>(loadEntry -> {

        });
    }
}