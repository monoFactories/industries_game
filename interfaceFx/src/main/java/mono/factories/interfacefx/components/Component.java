package mono.factories.interfacefx.components;

import javafx.scene.layout.AnchorPane;
import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

public abstract class Component implements Identifiable {
    protected final Identifier id;
    protected final AnchorPane pane;

    public Component(Identifier id) {
        this.id = id;
        this.pane = new AnchorPane();
    }

    @Override
    public final Identifier id() {
        return id;
    }

    public final AnchorPane getPane() {
        return pane;
    }

    public abstract void update();
    public abstract void onAdd();
    public abstract void onRemove();
}
