package mono.factories.interfacefx.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import mono.factories.interfacefx.components.Component;
import mono.factories.interfacefx.nodeparameters.NodeParameters;
import mono.factories.interfacefx.nodeparameters.NodeParametersParser;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.storage.Storage2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

public class FXMLComponentLoader {
    private static final Logger logger = LogManager.getLogger(FXMLComponentLoader.class);
    /**
     * Parses FXML markup from an InputStream, loads it into an AnchorPane, and recursively processes node parameters stored in userData as JSON strings.
     *
     * The method performs the following steps:
     * 1. Validates the input (checks that fxmlInput is not null).
     * 2. Uses FXMLLoader to load the FXML markup from the provided InputStream into an AnchorPane.
     * 3. Creates a recursive processor (Consumer<Node>) to traverse the node tree and process each node:
     *    - If the node is a Parent (container), the method recursively processes all its child nodes.
     *    - If the node has userData in the form of a String, the method attempts to parse this string as JSON using Gson and convert it into a NodeParameters object.
     *    - The parsed NodeParameters object replaces the original String in the node's userData.
     *    - If JSON parsing fails, a warning is logged, but the method continues processing other nodes.
     * 4. Applies the processor to the root AnchorPane, triggering the recursive traversal and parameter parsing.
     * 5. Returns the processed AnchorPane.
     *
     * Logging is used at multiple levels to track the process:
     * - INFO: marks key stages (start/end of FXML loading, start/end of recursive traversal).
     * - DEBUG: provides details about each node (type, number of children, userData processing status).
     * - WARN: logs JSON parsing errors for individual nodes.
     * - ERROR: logs critical errors (IO exceptions, unexpected errors during parsing).
     *
     * Error handling:
     * - Throws IOException if FXML loading from InputStream fails.
     * - Catches and logs other exceptions during parsing.
     * - Returns null if an error occurs (with an ERROR log message).
     *
     * Use case:
     * This method is useful for applications that store node configuration (e.g., styling, behavior parameters) in userData as JSON and need to parse and apply these parameters after FXML loading.
     *
     * @param fxmlInput InputStream containing FXML markup to be loaded and parsed.
     * @return Processed AnchorPane with parameters from userData parsed and applied, or null if an error occurred.
     */
    private static AnchorPane parse(InputStream fxmlInput) {
        Objects.requireNonNull(fxmlInput, "fxmlInput is null");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            logger.info("Starting to load FXML markup from InputStream.");
            AnchorPane anchorPane = fxmlLoader.load(fxmlInput);
            logger.info("FXML markup loaded successfully. Creating a node parameters processor.");
            AtomicReference<Consumer<Node>> parseJsonParameter_ = new AtomicReference<>();
            Consumer<Node> parseJsonParameter = node -> {
                if (node instanceof Parent parent) {
                    logger.debug("Processing container node: {} (number of children: {})",
                            node.getClass().getSimpleName(), parent.getChildrenUnmodifiable().size());
                    parent.getChildrenUnmodifiable().forEach(node1 -> parseJsonParameter_.get().accept(node1));
                } else {
                    logger.debug("Node {} is not a container, skipping recursive traversal.", node.getClass().getSimpleName());
                }
                if (node.getUserData() instanceof String str) {
                    try {
                        logger.debug("UserData string found for node {}, starting JSON parsing.", node.getClass().getSimpleName());
                        NodeParameters nodeParameters = NodeParametersParser.parserGson.fromJson(str, NodeParameters.class);
                        node.setUserData(nodeParameters);
                        logger.debug("JSON parsed successfully and saved to userData for node {}.", node.getClass().getSimpleName());
                    } catch (Exception e) {
                        logger.warn("Error parsing JSON for userData of node {}: {}", node.getClass().getSimpleName(), e.getMessage());
                    }
                } else {
                    logger.debug("UserData for node {} is not a string, JSON parsing is not required.", node.getClass().getSimpleName());
                }
            };
            parseJsonParameter_.set(parseJsonParameter);
            logger.info("Starting recursive traversal of the node tree to process parameters.");
            parseJsonParameter.accept(anchorPane);
            logger.info("Recursive traversal of the node tree completed. Parameter parsing finished.");
            return anchorPane;
        } catch (IOException e) {
            logger.error("Error loading FXML from InputStream: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error during FXML and node parameters parsing: {}", e.getMessage(), e);
        }
        logger.error("Returning null due to parsing error.");
        return null; // or throw new RuntimeException("Parsing failed", e) — depending on the application logic
    }
    private static Component load(InputStream is, Identifier id, Function<Storage2<Identifier, AnchorPane>, Component> createComponentFunction) {
        AnchorPane ap = parse(is);
        if (ap != null) {
            return createComponentFunction.apply(new Storage2<>(id, ap));
        }
        return null;
    }
}
