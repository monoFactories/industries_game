package mono.factories.core.interfaces.engine;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.core.registry.RegistryContainer;
import mono.factories.interfacefx.components.Component;
import mono.factories.registries.id.Identifier;

import java.util.Iterator;
import java.util.function.Consumer;

public class UIController {
    private static UIEngine engine;

    // add, remove Component and more
    static void init(UIEngine engine) {
        UIController.engine = engine;
    }

    public static void addComponent(Identifier addedID, Identifier parent) {
        baseAdd(addedID, parent, false);
    }

    private static void baseAdd(Identifier addedID, Identifier parent, boolean moveTo) {
        consumer((ui) -> {
            Component addedC = RegistryContainer.UI_COMPONENTS.a().get(addedID);
            if (addedC != null && !ui.active.contains(addedC)) {
                ComparisonsMap cm = ui.comparisons;
                ui.active.add(addedC);
                ui.rootPane.getChildren().add(addedC.getPane());
                cm.childToParent.put(addedID, parent);
                cm.parentToChildren.get(parent).add(addedID);
                ObjectArrayList<Identifier> needRemove = new ObjectArrayList<>();
                // починить чтобы не было зацикливания
                needRemove.addAll(cm.parentToChildren.get(addedID));// A -> B, B -> A
                while (!needRemove.isEmpty()) {
                    ObjectArrayList<Identifier> tempAdd = new ObjectArrayList<>();
                    for (Identifier id : needRemove) {
                        tempAdd.addAll(cm.parentToChildren.get(id));
                        Component c = RegistryContainer.UI_COMPONENTS.a().get(id);
                        if (c != null) {
                            ui.active.remove(c);
                            cm.childToParent.remove(id);
                            cm.parentToChildren.remove(id);
                        }
                    }
                    needRemove.clear();
                    needRemove.addAll(tempAdd);
                }
            }
        });
    }

    private static void checkEngine() {
        if (engine == null) throw new IllegalStateException("uiEngine not initialized");
    }

    private static void consumer(Consumer<UIEngine> r) {
        checkEngine();
        synchronized (engine.actionLock) {
            engine.actions.add(r);
        }
    }
}


