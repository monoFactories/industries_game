package mono.factories.events;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.MultiValueRegistry;
import mono.factories.registries.registry.MultiValueRegistryImpl;
import mono.factories.registries.registry.Registry;

public class EventRegistry<T> {
    private final MultiValueRegistry<EventHandler<T>> events = new MultiValueRegistryImpl<>();

    public MultiValueRegistry<EventHandler<T>> getEvents() {
        return events;
    }

    public void execute(Identifier id, T t) {
        if (id != null) {
            Registry<EventHandler<T>> actions = events.get(id);
            Event<T> event = new Event<>(t);
            if (actions != null) {
                for (EventHandler<T> eht : actions) {
                    EventHandlerState state = eht.run(event);
                    if (state == EventHandlerState.FINISH) {
                        break;
                    }
                }
            }
        }
    }
}
