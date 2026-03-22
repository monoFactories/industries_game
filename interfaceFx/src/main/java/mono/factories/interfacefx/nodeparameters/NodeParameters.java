package mono.factories.interfacefx.nodeparameters;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import javafx.scene.Node;
import mono.factories.registries.actions.ActionDataHolder;
import mono.factories.registries.actions.StandardActionDataHolder;
import mono.factories.registries.id.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NodeParameters {
    //action: {type: [actionId, ....], ...}
    public static final Logger logger = LogManager.getLogger(NodeParameters.class);
    private final NodeParameterActions actions;
    private final Object2ObjectOpenHashMap<Identifier, String> data;

    public NodeParameters() {
        this(new NodeParameterActions(), new Object2ObjectOpenHashMap<>());
    }

    public NodeParameters(NodeParameterActions actions, Object2ObjectOpenHashMap<Identifier, String> data) {
        if (actions == null || data == null) {
            throw new IllegalArgumentException("Actions and data cannot be null");
        }
        this.actions = actions;
        this.data = data;
        logger.debug("NodeParameters created with actions: {}, data size: {}", actions, data.size());
    }

    public NodeParameterActions getActions() {
        return actions;
    }

    public Object2ObjectOpenHashMap<Identifier, String> getData() {
        return data;
    }

    // get NodeParameters from Node or create and set NodeParameters for Node
    public static NodeParameters create(Node node, boolean needSetIfNull) {
        Object o = node.getUserData();
        if (o instanceof NodeParameters np) {
            return np;
        }
        if (needSetIfNull) {
            NodeParameters newInstance = new NodeParameters();
            node.setUserData(newInstance);
            return newInstance;
        }
        return null;
    }
}