package mono.factories.engines.process;

import mono.factories.dependencies.DependencyResolver;
import mono.factories.engines.engine.Engine;
import mono.factories.registries.id.Identifier;

import java.util.List;

public class MultiProcess extends Process {
    private final DependencyResolver<Process> processes = new DependencyResolver<>();

    public MultiProcess(Identifier id, Identifier[] dependencies) {
        super(id, dependencies);
    }

    @Override
    public void run(Engine engine) {
        List<Process> processList = processes.resolve();
        processList.forEach(process -> {
            process.run(engine);
        });
    }

    public DependencyResolver<Process> getProcesses() {
        return processes;
    }
}