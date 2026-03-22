package mono.factories.math.vec;

public record Vec2D(double x, double y) implements Coordinate2D, Vector {
    public static final Vec2D ZERO = new Vec2D(0,0), ONE = new Vec2D(1,1);

    public Vec2D add(Vec2D v) {
        return new Vec2D(x + v.x, y + v.y);
    }

    public Vec2D sub(Vec2D v) {
        return new Vec2D(x - v.x, y - v.y);
    }

    public Vec2D multiply(double v) {
        return new Vec2D(x * v, y * v);
    }

    public double dot(Vec2D v) {
        return x * v.x + y * v.y;
    }

    public Vec2D norm() {
        return multiply(1 / len());
    }

    public double len() {
        return Math.sqrt(dot(ONE));
    }

    @Override
    public double[] getAsArray() {
        return new double []{x, y};
    }
}
