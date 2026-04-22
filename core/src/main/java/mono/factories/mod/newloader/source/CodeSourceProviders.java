package mono.factories.mod.newloader.source;

import mono.factories.dependencies.CheckedHasDependency;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.ZipFile;

public class CodeSourceProviders {
    public static final Registry<CodeSourceProvider> CODE_SOURCE_PROVIDERS = new StandardRegistry<>();
    public static final Storage2<Identifier, CodeSourceProvider> ZIP_CODE_SOURCE_PROVIDER;

    static {
        Identifier ZIP_CODE_SOURCE_PROVIDER_ID = new Identifier("zip_code_source_provider");
        ZIP_CODE_SOURCE_PROVIDER = CODE_SOURCE_PROVIDERS.registerStorage(ZIP_CODE_SOURCE_PROVIDER_ID, new CodeSourceProviderBase(ZIP_CODE_SOURCE_PROVIDER_ID, Identifier.EMPTY_ARRAY) {
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
            public CodeSource create(Path path) throws IOException {
                // Создаём ZipCodeSource только если путь поддерживается
                if (supports(path)) {
                    return new ZipCodeSource(new ZipFile(path.toFile()));
                } else {
                    throw new IOException("Path is not supported by ZIP Provider: " + path);
                }
            }
        });
    }

    public static abstract class CodeSourceProviderBase implements CodeSourceProvider {
        private final CheckedHasDependency checkedHasDependency;
        public CodeSourceProviderBase(Identifier id, Identifier[] dependencies) {
            checkedHasDependency = new CheckedHasDependency(dependencies, id);
        }
        @Override
        public final Identifier id() {
            return checkedHasDependency.id();
        }
        @Override
        public final Identifier[] dependencies() {
            return checkedHasDependency.dependencies();
        }
    }
}
