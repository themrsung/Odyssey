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
}
