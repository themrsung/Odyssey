package civitas.celestis.number;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Arrays;

/**
 * <h2>Vector2</h2>
 * <p>
 * A two-dimensional vector.
 * Non-finite values are not supported.
 * </p>
 */
@Immutable
public class Vector2 {
    //
    // Constants
    //

    /**
     * Absolute zero. Represents origin.
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    /**
     * Positive X unit vector.
     */
    public static final Vector2 POSITIVE_X = new Vector2(1, 0);

    /**
     * Positive Y unit vector.
     */
    public static final Vector2 POSITIVE_Y = new Vector2(0, 1);

    /**
     * Negative X unit vector.
     */
    public static final Vector2 NEGATIVE_X = new Vector2(-1, 0);

    /**
     * Negative Y unit vector.
     */
    public static final Vector2 NEGATIVE_Y = new Vector2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x X value of this vector
     * @param y Y value of this vector
     */
    public Vector2(double x, double y) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
    }

    //
    // Variables
    //

    private final double x;
    private final double y;

    //
    // Getters
    //

    /**
     * Gets the X value of this vector.
     *
     * @return X value
     */
    public double x() {
        return x;
    }

    /**
     * Gets the Y value of this vector.
     *
     * @return Y value
     */
    public double y() {
        return y;
    }

    /**
     * Gets the magnitude of this vector.
     * This operation includes {@link Math#sqrt(double)}.
     *
     * @return Magnitude
     */
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Gets the squared magnitude of this vector.
     *
     * @return Squared magnitude
     */
    public double magnitude2() {
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    //
    // Vector-Scalar Arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param s Scalar to add
     * @return Resulting vector
     */
    @Nonnull
    public Vector2 add(double s) {
        return new Vector2(x + s, y + s);
    }

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s Scalar to subtract
     * @return Resulting vector
     */
    @Nonnull
    public Vector2 subtract(double s) {
        return new Vector2(x - s, y - s);
    }

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s Scalar to multiply with
     * @return Resulting vector
     */
    @Nonnull
    public Vector2 multiply(double s) {
        return new Vector2(x * s, y * s);
    }

    /**
     * Divides this vector by a scalar.
     *
     * @param s Scalar to use as denominator
     * @return Resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    public Vector2 divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");
        return new Vector2(x / s, y / s);
    }

    //
    // Vector-Vector Arithmetic
    //

    /**
     * Adds another vector to this vector.
     *
     * @param v Vector to add
     * @return Resulting vector
     */
    @Nonnull
    public Vector2 add(@Nonnull Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v Vector to subtract
     * @return Resulting vector
     */
    @Nonnull
    public Vector2 subtract(@Nonnull Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector.
     *
     * @param v Vector to multiply with
     * @return Product of two vectors
     */
    @Nonnull
    public Vector2 multiply(@Nonnull Vector2 v) {
        return new Vector2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * Gets the dot product between {@code this} and {@code v}.
     *
     * @param v Vector to multiply with
     * @return Dot product of two vectors
     */
    public double dot(@Nonnull Vector2 v) {
        return x * v.x + y * v.y;
    }

    //
    // Util
    //

    /**
     * Negates this vector.
     *
     * @return Negated vector
     */
    @Nonnull
    public Vector2 negate() {
        return multiply(-1);
    }

    /**
     * Normalizes this vector to a unit vector.
     *
     * @return Normalized unit vector
     */
    @Nonnull
    public Vector2 normalize() {
        return multiply(Numbers.isqrt(magnitude2()));
    }

    /**
     * Gets the distance from {@code this} and {@code v}.
     *
     * @param v Vector to get distance to
     * @return Distance between two vectors
     */
    @Nonnegative
    public double distance(@Nonnull Vector2 v) {
        return subtract(v).magnitude();
    }

    /**
     * Gets the squared distance from {@code this} to {@code v}.
     *
     * @param v Vector to get distance to
     * @return Squared distance between two vectors
     */
    @Nonnegative
    public double distance2(@Nonnull Vector2 v) {
        return subtract(v).magnitude2();
    }

    /**
     * Rotates this vector counter-clockwise by given angle.
     *
     * @param angle Angle in radians
     * @return Rotated vector
     */
    @Nonnull
    public Vector2 rotate(double angle) {
        return multiply(new Vector2(Math.cos(angle), Math.sin(angle)));
    }

    //
    // Comparison
    //


    /**
     * Checks for equality.
     *
     * @param obj Object to compare to
     * @return {@code true} if given object is a two-dimensional number with the same scalar components
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector2 v)) return false;
        return x == v.x && y == v.y;
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string to a vector.
     *
     * @param s String to deserialize
     * @return Deserialized vector
     * @throws NumberFormatException When the string is not parsable to a vector
     */
    @Nonnull
    public static Vector2 parseVector(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("Vector2{")) throw new NumberFormatException("Given string is not a vector.");

        final String[] strings = s
                .replaceAll("Vector2\\{", "")
                .replaceAll("}", "")
                .split(", ");

        final double[] values = {Double.NaN, Double.NaN};

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a vector.");

            switch (split[0]) {
                case "x" -> values[0] = Double.parseDouble(split[1]);
                case "y" -> values[1] = Double.parseDouble(split[1]);
            }
        });

        return new Vector2(values[0], values[1]);
    }

    /**
     * Converts this vector to a string.
     *
     * @return Stringified vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
