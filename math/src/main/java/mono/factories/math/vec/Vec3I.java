package mono.factories.math.vec;

public record Vec3I(int x, int y, int z) implements Coordinate3I, Vector {
    public static final Vec3I ZERO = new Vec3I(0,0, 0), ONE = new Vec3I(1,1, 1);

    public Vec3I add(Vec3I v) {
        return new Vec3I(x + v.x, y + v.y, z + v.z);
    }

    public Vec3I sub(Vec3I v) {
        return new Vec3I(x - v.x, y - v.y, z - v.z);
    }

    public Vec3I multiply(int v) {
        return new Vec3I(x * v, y * v, z * v);
    }

    public int dot(Vec3I v) {
        return x * v.x + y * v.y;
    }

    public Vec3I norm() {
        double len = len();
        return new Vec3I((int) len * x, (int) len * y, (int) len * z);
    }

    public double len() {
        return Math.sqrt(dot(ONE));
    }

    @Override
    public double[] getAsArray() {
        return new double[]{x, y, z};
    }
}
