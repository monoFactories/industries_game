package mono.factories.math.vec;

public record Vec3D(double x, double y, double z) implements Coordinate3D, Vector {
    public static final Vec3D ZERO = new Vec3D(0,0, 0), ONE = new Vec3D(1,1, 1);

    public Vec3D add(Vec3D v) {
        return new Vec3D(x + v.x, y + v.y, z + v.z);
    }

    public Vec3D sub(Vec3D v) {
        return new Vec3D(x - v.x, y - v.y, z - v.z);
    }

    public Vec3D multiply(double v) {
        return new Vec3D(x * v, y * v, z * v);
    }

    public double dot(Vec3D v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vec3D norm() {
        return multiply(1 / len());
    }

    public double len() {
        return Math.sqrt(dot(ONE));
    }

    @Override
    public double[] getAsArray() {
        return new double[] {x, y, z};
    }
}

