package mono.factories.interfacefx.handlers;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CustomEventHandler<T extends Event> implements EventHandler<T> {
    final ObjectArrayList<EventHandler<T>> eventHandlers = new ObjectArrayList<>();

    public CustomEventHandler() {

    }

    public CustomEventHandler(EventHandler<T> t) {
        add(t);
    }

    @Override
    public void handle(T t) {
        eventHandlers.forEach(eh -> eh.handle(t));
    }

    public void add(EventHandler<T> handler) {
        if (handler != null) {
            if (handler instanceof CustomEventHandler<T> ceh) {
                eventHandlers.addAll(ceh.eventHandlers);
            } else {
                eventHandlers.add(handler);
            }
        }
    }

    public void removeLast() {
        eventHandlers.removeLast();
    }

    int size() {
        return eventHandlers.size();
    }
}
