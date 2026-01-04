package mono.factories.interfacefx.nodeparameters;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mono.factories.registries.id.Identifier;

public class NodeParameters {
    private final NodeParameterActions actions = new NodeParameterActions();
    private final Object2ObjectOpenHashMap<Identifier, String> data = new Object2ObjectOpenHashMap<>();

    public NodeParameterActions getActions() {
        return actions;
    }

    public Object2ObjectOpenHashMap<Identifier, String> getData() {
        return data;
    }
}