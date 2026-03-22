package mono.factories.math.vec;

public record Vec2I(int x, int y) implements Coordinate2I, Vector {
    public static final Vec2I ZERO = new Vec2I(0,0), ONE = new Vec2I(1,1);

    public Vec2I add(Vec2I v) {
        return new Vec2I(x + v.x, y + v.y);
    }

    public Vec2I sub(Vec2I v) {
        return new Vec2I(x - v.x, y - v.y);
    }

    public Vec2I multiply(int v) {
        return new Vec2I(x * v, y * v);
    }

    public int dot(Vec2I v) {
        return x * v.x + y * v.y;
    }

    public Vec2I norm() {
        double len = len();
        return new Vec2I((int) len * x, (int) len * y);
    }

    public double len() {
        return Math.sqrt(dot(ONE));
    }

    @Override
    public double[] getAsArray() {
        return new double[]{x, y};
    }
}
