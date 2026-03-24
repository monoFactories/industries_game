package mono.factories.interfacefx.components;

import javafx.scene.layout.AnchorPane;
import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Component component)) return false;
        return Objects.equals(id, component.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
