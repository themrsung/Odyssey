package civitas.celestis.number;

/**
 * <h2>Numbers</h2>
 * <p>A numerical utility class.</p>
 */
public final class Numbers {
    /**
     * Denotes explicitly that a given field requires a finite value.
     *
     * @param v Value to check
     * @return Value given as parameter
     */
    public static double requireFinite(double v) {
        if (!Double.isFinite(v)) throw new IllegalArgumentException("Given field requires a finite double.");

        return v;
    }

    /**
     * Gets the inverse square root of given number.
     *
     * @param x Number to inverse square root
     * @return Inverse square root
     */
    public static double isqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf * x * x);
        return x;
    }
}
