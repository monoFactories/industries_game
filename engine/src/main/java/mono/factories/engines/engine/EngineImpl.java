package mono.factories.engines.engine;

import mono.factories.dependencies.DependencyResolver;
import mono.factories.engines.process.Process;

import java.util.ArrayList;
import java.util.List;

public class EngineImpl extends Engine {
    private final List<Process> processes = new ArrayList<>();
    private volatile boolean paused = false;
    private volatile boolean running = false;
    private final Object lock = new Object();
    private final DependencyResolver<Process> resolver = new DependencyResolver<>();

    private final Thread thread = new Thread(() -> {
        running = true;
        try {
            while (running) {
                synchronized (lock) {
                    while (paused) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                for (Process process : processes) {
                    if (!running) break;
                    process.run(this);
                }
                List<Process> updatedProcesses = resolver.resolve();
                processes.clear();
                processes.addAll(updatedProcesses);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            running = false;
        }
    });

    @Override
    public void start() {
        if (!thread.isAlive()) {
            thread.start();
        }
        synchronized (lock) {
            paused = false;
            lock.notifyAll();
        }
    }

    @Override
    public void pause() {
        synchronized (lock) {
            paused = true;
        }
    }

    @Override
    public void end() {
        synchronized (lock) {
            running = false;
            paused = false;
            lock.notifyAll();
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public DependencyResolver<Process> getResolver() {
        return resolver;
    }
}