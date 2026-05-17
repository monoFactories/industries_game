package mono.factories.mod.newloader.fun;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.ListRegistry;
import mono.factories.registries.registry.ListRegistryImpl;

import java.util.List;
import java.util.Objects;

public class LoadingFunctionSuppliersStorage {
    public final ListRegistry<FunctionWithData> stageSuppliers = new ListRegistryImpl<>();
    public final List<Identifier> order = new ObjectArrayList<>();
    public Identifier currentStage;

    public void add(FunctionWithData added) {
        if (!stageSuppliers.contains(currentStage)) {
            stageSuppliers.register(currentStage, new ObjectArrayList<>());
        }
        List<FunctionWithData> stageList = stageSuppliers.get(currentStage);
        stageList.add(added);
    }

    public void remove(FunctionWithData added) {
        if (!stageSuppliers.contains(currentStage)) {
            stageSuppliers.register(currentStage, new ObjectArrayList<>());
        }
        List<FunctionWithData> stageList = stageSuppliers.get(currentStage);
        stageList.remove(added);
    }

    public record FunctionWithData(Identifier function, JsonElement data) {
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof FunctionWithData that)) return false;
            return Objects.equals(data, that.data) && Objects.equals(function, that.function);
        }

        @Override
        public int hashCode() {
            return Objects.hash(function, data);
        }
    }
}
