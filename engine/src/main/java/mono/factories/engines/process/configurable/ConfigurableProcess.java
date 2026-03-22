package mono.factories.engines.process.configurable;

import mono.factories.engines.engine.Engine;
import mono.factories.engines.process.Process;
import mono.factories.engines.process.ProcessRegistries;
import mono.factories.registries.actions.ActionDataHolder;
import mono.factories.registries.actions.StandardActionDataHolder;
import mono.factories.utils.exceptions.StopForEachException;

public class ConfigurableProcess extends Process {
    private final ActionDataHolder<Engine> actionData = new StandardActionDataHolder<>(storage3 -> {
        ConfigurableProcessParameter cpp = ProcessRegistries.PROCESS_PARAMETERS.get(storage3.a());
        if (cpp != null) {
            boolean canRun = cpp.canRun(storage3.c(), storage3.b(), this);
            if (!canRun) throw new StopForEachException();
        }
    });
    private final Process rootProcess;

    public ConfigurableProcess(Process nurnBergProcess) {
        super(nurnBergProcess.id(), nurnBergProcess.dependencies());
        rootProcess = nurnBergProcess;// :)
    }

    @Override
    public final void run(Engine engine) {
        try {
            actionData.runAllAction(engine);
            rootProcess.run(engine);
        } catch (StopForEachException ignored) {
        }
    }

    public ActionDataHolder<Engine> getActionData() {
        return actionData;
    }

    public Process getRootProcess() {
        return rootProcess;
    }
}
