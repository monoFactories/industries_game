package mono.factories.mod.newloader.source;

import mono.factories.dependencies.HasDependency;

import java.nio.file.Path;

public interface CodeSourceProvider {
    boolean supports(Path path);
    CodeSource create(Path path) throws Exception;
}
