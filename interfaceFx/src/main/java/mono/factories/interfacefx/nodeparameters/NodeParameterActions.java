package mono.factories.interfacefx.nodeparameters;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.registries.id.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NodeParameterActions {
    private final Object2ObjectOpenHashMap<Identifier, ObjectArrayList<Identifier>> actions = new Object2ObjectOpenHashMap<>();

    public void add(Identifier type, Identifier action) {
        actions.computeIfAbsent(type, k -> new ObjectArrayList<>()).add(action);
    }

    public ObjectArrayList<Identifier> get(Identifier type, Identifier action) {
        return actions.get(type);
    }

    public void remove(Identifier type, Identifier action) {
        ObjectArrayList<Identifier> actionTypes = actions.get(type);
        if (actionTypes != null) {
            actionTypes.remove(action);
            if (actionTypes.isEmpty()) {
                actions.remove(type);
            }
        }
    }

    public void forEachType(BiConsumer<Identifier, ObjectArrayList<Identifier>> a) {
        actions.forEach(a);
    }

    public void forEachInType(Identifier type, Consumer<Identifier> a) {
        ObjectArrayList<Identifier> actionTypes = actions.get(type);
        if (actionTypes != null) {
            actionTypes.forEach(a);
        }
    }
}
