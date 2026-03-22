package mono.factories.core.inital;

import mono.factories.engines.engine.Engine;
import mono.factories.engines.engine.EngineImpl;

public class EngineInit {
    static void init() {
        Engine engine = new EngineImpl();
        Engine.setEngine(engine);
    }
}
