package mono.factories.mod.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class ModClassLoader extends URLClassLoader {
    public static final ModClassLoader classLoader = new ModClassLoader(new URL[0]);

    public ModClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
