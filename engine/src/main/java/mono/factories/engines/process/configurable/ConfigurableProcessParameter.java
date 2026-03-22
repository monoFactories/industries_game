package mono.factories.engines.process.configurable;

import mono.factories.engines.engine.Engine;
import mono.factories.registries.actions.ActionDataHolder;
import mono.factories.registries.id.Identifiable;

public interface ConfigurableProcessParameter extends Identifiable {
    boolean canRun(Engine e, ActionDataHolder<Engine> actionDataHolder, ConfigurableProcess configurableProcess);
}
