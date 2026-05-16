package mono.factories.mod.newloader.loader;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.registry.Registry;

import java.util.List;

public final class SimpleCodeSourceModLoader extends CodeSourceModLoader {
    private final List<CodeSource> codeSources;

    private SimpleCodeSourceModLoader(List<CodeSource> codeSources) {
        this.codeSources = codeSources;
    }

    static SimpleCodeSourceModLoader create() {
        return new SimpleCodeSourceModLoader(new ObjectArrayList<>());
    }

    @Override
    public void addCodeSource(CodeSource source) {
        codeSources.add(source);
    }

    @Override
    public synchronized void launch(Registry<Object> context) {

    }
}