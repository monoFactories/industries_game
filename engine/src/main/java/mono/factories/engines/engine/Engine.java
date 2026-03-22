package mono.factories.engines.engine;

import mono.factories.dependencies.DependencyResolver;
import mono.factories.engines.process.Process;

public abstract class Engine {
    private static volatile Engine engine;

    protected Engine() {
    }

    public abstract void start();

    public abstract void pause();

    public abstract void end();

    public abstract DependencyResolver<Process> getResolver();

    public static Engine getEngine() {
        return engine;
    }

    public static synchronized void setEngine(Engine newEngine) {
        if (newEngine == null) {
            throw new IllegalArgumentException("Engine cannot be null");
        }

        if (engine != null) {
            engine.end();
        }
        engine = newEngine;
    }
}
