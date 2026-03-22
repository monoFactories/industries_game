package mono.factories.events;

public interface EventHandler<T> {
    EventHandlerState run(Event<T> event);
}
