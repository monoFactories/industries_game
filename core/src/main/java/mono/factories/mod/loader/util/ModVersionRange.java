package mono.factories.mod.loader.util;

import io.github.g00fy2.versioncompare.Version;

import java.util.Objects;
import java.util.function.Function;

public class ModVersionRange {
    public static final String ALWAYS_TRUE = "*";

    private final String original;
    private final Function<Version, Boolean> rangeFunction;

    private ModVersionRange(Function<Version, Boolean> rangeFunction, String original) {
        this.rangeFunction = rangeFunction;
        this.original = original;
    }

    public boolean inRange(Version v) {
        return rangeFunction.apply(v);
    }

    @Override
    public String toString() {
        return original;
    }

    public static ModVersionRange parse(String versionRange) {
        Function<Version, Boolean> rangeFunction = null;
        Objects.requireNonNull(versionRange, "versionRange is null");
        if (versionRange.equals(ALWAYS_TRUE)) {
            rangeFunction = v -> true;
        } else if (versionRange.length() < 3) {
            throw new IllegalArgumentException("version range length less than 3");
        } else {
            String operator = versionRange.substring(0, 2);
            String o = versionRange.substring(2);
            if (operator.equals("<=")) {
                Version ver = new Version(o, true);
                rangeFunction = v -> v.compareTo(ver) <= 0;
            } else if (operator.equals(">=")) {
                Version ver = new Version(o, true);
                rangeFunction = v -> v.compareTo(ver) >= 0;
            } else if (operator.equals("==")) {
                Version ver = new Version(o, true);
                rangeFunction = v -> v.compareTo(ver) == 0;
            } else if (operator.equals("><")) {
                String[] versions = o.split(";", 2);
                Version a = new Version(versions[0], true);
                Version b = new Version(versions[1], true);
                rangeFunction = v -> v.compareTo(a) >= 0 && v.compareTo(b) <= 0;
            } else {
                throw new IllegalArgumentException("couldn't find version operator: " + operator);
            }
        }
        return new ModVersionRange(rangeFunction, versionRange);
    }
}
