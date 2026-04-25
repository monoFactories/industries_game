package mono.factories.mod.newloader.source.provider;

import mono.factories.dependencies.HasTag;

import java.nio.file.Path;

public interface CodeSourceProvider extends HasTag {
    boolean supports(Path path);
    CodeSource create(Path path, ProviderTagPath parentTags) throws Exception;
}
