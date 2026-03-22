package mono.factories.interfacefx.handlers;

import javafx.event.Event;
import javafx.event.EventHandler;

public class EventHandlerWrapper<T extends Event> {
    private final CustomEventHandler<T> handler;

    public EventHandlerWrapper() {
        handler = new CustomEventHandler<>();
    }

    public EventHandlerWrapper(EventHandler<T> eventHandler) {
        handler = new CustomEventHandler<>(eventHandler);
    }

    public EventHandlerWrapper<T> add(EventHandler<T> v) {
        handler.add(v);
        return this;
    }

    public EventHandlerWrapper<T> removeLast() {
        handler.removeLast();
        return this;
    }

    public EventHandler<T> build() {
        if (handler.size() == 1) {
            return handler.eventHandlers.getFirst();
        } else {
            return handler;
        }
    }
}
