package mono.factories.mod.loader.util;

import it.unimi.dsi.fastutil.objects.ObjectImmutableList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import mono.factories.dependencies.HasDependency;
import mono.factories.registries.id.Identifier;

import java.util.List;

public abstract class ModLoadingEntry implements HasDependency {
    public static final Identifier[] EMPTY_DEPENDENCIES = new Identifier[0];

    private final Identifier id;
    private final List<Identifier> dependencies;

    public ModLoadingEntry(Identifier id, List<Identifier> dependencies) {
        this.id = id;
        this.dependencies = new ObjectImmutableList<>(dependencies);
    }

    public abstract void load();

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public Identifier[] dependencies() {
        return dependencies.toArray(EMPTY_DEPENDENCIES);
    }
}
