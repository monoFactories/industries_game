package mono.factories.mod.entrypoint;

import java.lang.reflect.Method;

public class ModEntryPointFinder {
    public static Method entryPointMethod(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ModEntryPoint.class)) {
                return method;
            }
        }
        return null;
    }
}
