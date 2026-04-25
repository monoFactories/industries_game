package mono.factories.mod.newloader.source.provider;

import mono.factories.mod.newloader.source.ZipCodeSource;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.zip.ZipFile;

public class CodeSourceProviders {
    public static final Registry<CodeSourceProvider> CODE_SOURCE_PROVIDERS = new StandardRegistry<>();
    public static final Storage2<Identifier, CodeSourceProvider> ZIP_CODE_SOURCE_PROVIDER;

    private static final class Tags {
        public static final Identifier
        ZIP_BASED_ARCHIVE = new Identifier("zip_based_archive")
        ;
    }
    static {
        Identifier ZIP_CODE_SOURCE_PROVIDER_ID = new Identifier("zip_provider");
        ZIP_CODE_SOURCE_PROVIDER = CODE_SOURCE_PROVIDERS.registerStorage(
                new Identifier("zip_provider"),
                new CodeSourceProviderBase(
                        ZIP_CODE_SOURCE_PROVIDER_ID,
                        new Identifier[]{Tags.ZIP_BASED_ARCHIVE},
                        Identifier.EMPTY_ARRAY
                        ) {
            @Override
            public boolean supports(Path path) {
                if (path == null || !Files.exists(path) || !Files.isRegularFile(path)) return false;
                try (InputStream is = Files.newInputStream(path)) {
                    int b1 = is.read(), b2 = is.read(),
                            b3 = is.read(), b4 = is.read();
                    return b1 == 0x50 && b2 == 0x4B && b3 == 0x03 && b4 == 0x04;
                } catch (IOException e) {
                    return false;
                }
            }

            @Override
            public CodeSource create(Path path, ProviderTagPath parentTags) throws IOException {
                if (supports(path)) {
                    return new ZipCodeSource(new ZipFile(path.toFile()));
                } else {
                    throw new IOException("Path is not supported by ZIP Provider: " + path);
                }
            }
        });
    }

    public static abstract class CodeSourceProviderBase implements CodeSourceProvider {
        private final Identifier[] tags;
        private final Identifier[] dependencies;
        private final Identifier id;
        public CodeSourceProviderBase(Identifier id, Identifier[] tags, Identifier[] dependencies) {
            this.tags = tags;
            this.dependencies = dependencies;
            this.id = id;
        }

        @Override
        public Identifier[] tags() {
            return Arrays.copyOf(tags, tags.length);
        }

        @Override
        public Identifier[] dependencies() {
            return Arrays.copyOf(dependencies, dependencies.length);
        }

        @Override
        public Identifier id() {
            return id;
        }
    }
}
