package mono.factories.core.interfaces.engine;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import mono.factories.core.registry.RegistryContainer;
import mono.factories.interfacefx.components.Component;
import mono.factories.registries.id.Identifier;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
/*
_A->B->_C:
1. add(D, C):
 */

public class UIController {
    private static UIEngine engine;

    // add, remove Component and more
    static void init(UIEngine engine) {
        UIController.engine = engine;
    }

    public static Identifier[] getAllChildren(Identifier target) {
        try {
            checkEngine();
            UIEngine ui = UIEngine.getInstance();
            synchronized (UIEngine.class) {
                return getAllChildren(ui, target).toArray(new Identifier[0]);
            }
        } catch (Exception e) {
            return new Identifier[0];
        }
    }

    public static void remove(Identifier target) {
        if (target != null) {
            consumer(ui -> {
                Collection<Identifier> children = getAllChildren(ui, target);
                children.forEach(child -> {
                    ui.comparisons.childToParent.remove(child);
                    ui.comparisons.parentToChildren.remove(child);
                    Component component = RegistryContainer.UI_COMPONENTS.a().get(child);
                    if (component != null) {
                        ui.active.remove(component);
                        ui.rootPane.getChildren().remove(component.getPane());
                    }
                });
                ui.comparisons.childToParent.remove(target);
                ui.comparisons.parentToChildren.remove(target);
                Component component = RegistryContainer.UI_COMPONENTS.a().get(target);
                if (component != null) {
                    ui.active.remove(component);
                    ui.rootPane.getChildren().remove(component.getPane());
                }
            });
        }
    }

    //can be used as remove
    public static void moveTo(Identifier target, Identifier parent) {
        baseAdd(target, parent, true);
    }

    public static void addComponent(Identifier addedID, Identifier parent) {
        baseAdd(addedID, parent, false);
    }

    private static void baseAdd(Identifier addedID, Identifier parent, boolean moveTo) {
        consumer(ui -> {
            List<Component> active = ui.active;
            Component addedC = RegistryContainer.UI_COMPONENTS.a().get(addedID);
            boolean isRoot = active.isEmpty();
            addComponent0(addedC, parent, ui, isRoot);
            if (moveTo) {
                Component parentC = RegistryContainer.UI_COMPONENTS.a().get(parent);
                if (parentC != null) {
                    ui.rootPane.getChildren().remove(parentC.getPane());
                    active.remove(parentC);
                }
            }
        });
    }

    private static void addComponent0(Component c, Identifier parent, UIEngine ui, boolean isRoot) {
        add_(ui, c);
        if (isRoot) {
            ui.comparisons.rootComponent = c.id();
        } else {
            ui.comparisons.childToParent.put(c.id(), parent);
            ui.comparisons.addParentToChildrenEntry(ui, parent, c.id());
        }
        Collection<Identifier> children = getAllChildren(ui, c.id());
        children.forEach(child -> {
            ui.comparisons.childToParent.remove(child);
            ui.comparisons.parentToChildren.remove(child);
            Component component = RegistryContainer.UI_COMPONENTS.a().get(child);
            if (component != null) {
                remove_(ui, component);
            }
        });
    }

    private static Collection<Identifier> getAllChildren(UIEngine ui, Identifier targetID) {
        ComparisonsMap cm = ui.comparisons;
        ObjectOpenHashSet<Identifier> needReturn = new ObjectOpenHashSet<>();
        ObjectArrayList<Identifier> queue = new ObjectArrayList<>();
        Collection<Identifier> directChildren = cm.parentToChildren.get(targetID);
        if (directChildren != null) {
            queue.addAll(directChildren);
        }
        while (!queue.isEmpty()) {
            ObjectArrayList<Identifier> currentLevel = new ObjectArrayList<>();
            for (Identifier id : queue) {
                if (!needReturn.contains(id)) {
                    needReturn.add(id);
                    Collection<Identifier> children = cm.parentToChildren.get(id);
                    if (children != null) {
                        for (Identifier child : children) {
                            if (!needReturn.contains(child)) {
                                currentLevel.add(child);
                            }
                        }
                    }
                }
            }
            queue = currentLevel;
        }
        return needReturn;
    }

    private static void add_(UIEngine ui, Component c) {
        ui.active.add(c);
        ui.rootPane.getChildren().add(c.getPane());
    }

    private static void remove_(UIEngine ui, Component c) {
        ui.active.remove(c);
        ui.rootPane.getChildren().remove(c.getPane());
    }

    private static void checkEngine() {
        if (engine == null) throw new IllegalStateException("uiEngine not initialized");
    }

    private static void consumer(Consumer<UIEngine> r) {
        checkEngine();
        synchronized (UIEngine.class) {
            engine.actions.add(r);
        }
    }
}
