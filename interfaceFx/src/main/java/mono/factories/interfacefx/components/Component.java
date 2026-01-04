package mono.factories.interfacefx.components;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mono.factories.registries.id.Identifier;

public class Component {
    private final Identifier id;
    private final AnchorPane pane;

    public Component(Identifier id) {
        this.id = id;
        this.pane = new AnchorPane();
    }

    public Identifier getId() {
        return id;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void update() {

    }
}
