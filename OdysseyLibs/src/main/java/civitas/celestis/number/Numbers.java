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
     * Denotes explicitly that a given field requires a finite value.
     *
     * @param v Value to check
     * @return Value given as parameter
     */
    public static float requireFinite(float v) {
        if (!Float.isFinite(v)) throw new IllegalArgumentException("Given field requires a finite float.");

        return v;
    }

    /**
     * Gets the inverse square root of given number.
     *
     * @param x Number to inverse square root
     * @return Inverse square root
     */
    public static double isqrt(double x) {
        double result = x;
        double xhalf = 0.5d * result;

        long l = Double.doubleToLongBits(result);

        // Fast inverse square root
        l = 0x5fe6ec85e7de30daL - (l >> 1);

        result = Double.longBitsToDouble(l);

        // 4 iterations of Newton's method
        for (int i = 0; i < 4; i++) {
            result = result * (1.5d - xhalf * result * result);
        }

        return result;
    }

    /**
     * Gets the inverse square root of given number.
     *
     * @param x Number to inverse square root
     * @return Inverse square root
     */
    public static float isqrt(float x) {
        float result = x;
        float xhalf = 0.5f * result;

        int i = Float.floatToIntBits(x);

        // Fast inverse square root
        i = 0x5f3759df - (i >> 1);

        result = Float.intBitsToFloat(i);

        // 2 iterations of Newton's method
        for (int j = 0; j < 2; j++) {
            result = result * (1.5f - xhalf * result * result);
        }

        return result;
    }
}
