package mono.factories.mod.newloader.preparatory;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.mod.newloader.source.provider.CodeSource;
import mono.factories.registries.storage.DefaultHolder;

import java.util.List;
import java.util.function.Supplier;

public abstract class PreparatoryModLoader {
    public abstract void addCodeSource(CodeSource source);
    public abstract void launch();

    public static final DefaultHolder<Supplier<PreparatoryModLoader>> holder = new DefaultHolder<>(PreparatoryModLoader::simple);

    private static PreparatoryModLoader simple() {
        return SimplePreparatoryModLoader.create();
    }

    private static final class SimplePreparatoryModLoader extends PreparatoryModLoader {
        private final List<CodeSource> codeSources;

        public SimplePreparatoryModLoader(List<CodeSource> codeSources) {
            this.codeSources = codeSources;
        }

        static SimplePreparatoryModLoader create() {
            return new SimplePreparatoryModLoader(new ObjectArrayList<>());
        }

        @Override
        public void addCodeSource(CodeSource source) {
            codeSources.add(source);
        }

        @Override
        public synchronized void launch() {
            // write code
        }
    }

}
