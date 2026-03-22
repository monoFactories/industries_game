package mono.factories.math.vec;

import java.util.function.Function;

public class VectorCaster {
    public static final  Function<Double, Integer> STANDARD_DOUBLE_INT_CASTER = (in) -> (int) in.doubleValue();

    private final Function<Double, Integer> castInt;

    public VectorCaster(Function<Double, Integer> doubleToIntCaster) {
        this.castInt = doubleToIntCaster;
    }

    public Vec2D toVec2D(Vector v) {
        double[] values = v.getAsArray();
        int l = values.length;
        return new Vec2D(l > 0 ? values[0] : 0.0, l > 1 ? values[1] : 0.0);
    }

    public Vec2I toVec2I(Vector v) {
        double[] values = v.getAsArray();
        int l = values.length;
        return new Vec2I(l > 0 ? castInt.apply(values[0]) : 0, l > 1 ? castInt.apply(values[1]) : 0);
    }

    public Vec3D toVec3D(Vector v) {
        double[] values = v.getAsArray();
        int l = values.length;
        return new Vec3D(l > 0 ? values[0] : 0.0, l > 1 ? values[1] : 0.0, l > 2 ? values[2] : 0.0);
    }

    public Vec3I toVec3I(Vector v) {
        double[] values = v.getAsArray();
        int l = values.length;
        return new Vec3I(l > 0 ? castInt.apply(values[0]) : 0, l > 1 ? castInt.apply(values[1]) : 0, l > 2 ? castInt.apply(values[2]) : 0);
    }
}
