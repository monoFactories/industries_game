package mono.factories.mod.newloader.fun;

import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.Registry;
import mono.factories.registries.registry.StandardRegistry;
import mono.factories.registries.storage.Storage2;

public class LoadingFunctionSuppliers {
    public static final Registry<LoadingFunctionSupplier> registry = new StandardRegistry<>();
    public static final Storage2<Identifier, LoadingFunctionSupplier> STAGE;
    public static final Storage2<Identifier, LoadingFunctionSupplier> ADD;
    public static final Storage2<Identifier, LoadingFunctionSupplier> REMOVE;
    static {
        STAGE = registry.registerStorage(new Identifier("stage"), ((source, data, storage) -> {
            Identifier stage = Identifier.read(data.supplierData());
            if (stage != null) storage.currentStage = stage;
        }));
        ADD = registry.registerStorage(new Identifier("add"), ((source, data, storage) -> {
            Identifier add = Identifier.read(data.supplierData());
            if (add != null) storage.add(new LoadingFunctionSuppliersStorage.FunctionWithData(add, data.functionData()));
        }));
        REMOVE = registry.registerStorage(new Identifier("remove"), ((source, data, storage) -> {
            Identifier remove = Identifier.read(data.supplierData());
            if (remove != null) storage.remove(new LoadingFunctionSuppliersStorage.FunctionWithData(remove, data.functionData()));
        }));
    }
}
/*
_ = registry.registerStorage(new Identifier(""), ((source, data, storage) -> {

        }));
 */