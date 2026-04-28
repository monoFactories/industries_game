package mono.factories.mod.newloader.source.provider;

import java.nio.file.Path;

public interface CodeSourceProvider {
    boolean supports(Path path);
    CodeSource create(Path path) throws Exception;
}
