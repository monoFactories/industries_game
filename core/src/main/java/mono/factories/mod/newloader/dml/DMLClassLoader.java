package mono.factories.mod.newloader.dml;

import mono.factories.registries.LazyInitializer;

import java.net.URL;
import java.net.URLClassLoader;

public class DMLClassLoader extends URLClassLoader {
    public static final LazyInitializer<DMLClassLoader> classLoader = new LazyInitializer<>(DMLClassLoader::new);

    private DMLClassLoader() {
        super("dml_classloader", new URL[0], getSystemClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
