package mono.factories.engines.process;

import mono.factories.registries.id.Identifiable;
import mono.factories.registries.id.Identifier;

public class StandardProcessHolder implements Identifiable {
    private final Process process;
    private long lastMillisTimeRunning;


    public StandardProcessHolder(Process process) {
        if (process == null) throw new NullPointerException("process is null");
        this.process = process;
        lastMillisTimeRunning = System.currentTimeMillis();
    }

    @Override
    public Identifier id() {
        return process.id();
    }
}
