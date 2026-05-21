package mono.factories.mod.newloader.fun;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mono.factories.registries.id.Identifier;
import mono.factories.registries.registry.ListRegistry;
import mono.factories.registries.registry.ListRegistryImpl;

import java.util.List;
import java.util.Objects;

public final class LoadingFunctionSuppliersStorage {
    public static final Identifier START_STAGE = new Identifier("initial");

    public final ListRegistry<FunctionWithData> stageSuppliers = new ListRegistryImpl<>();
    public final List<Identifier> order = new ObjectArrayList<>();
    public Identifier currentStage = START_STAGE;

    public void add(FunctionWithData added) {
        List<FunctionWithData> stageList = initStage(true);
        stageList.add(added);
    }

    public void remove(FunctionWithData removed) {
        List<FunctionWithData> stageList = initStage(false);
        stageList.remove(removed);
    }

    private List<FunctionWithData> initStage(boolean needAddInOrder) {
        if (!stageSuppliers.contains(currentStage)) {
            if (needAddInOrder) order.add(currentStage);
            stageSuppliers.register(currentStage, new ObjectArrayList<>());
        }
        return stageSuppliers.get(currentStage);
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
