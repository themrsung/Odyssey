package civitas.celestis.number;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.Arrays;

/**
 * <h2>Vector</h2>
 * <p>
 * A three-dimensional vector.
 * Non-finite values are not supported.
 * </p>
 */
@Immutable
public class Vector3 implements Serializable {
    //
    // Constants
    //

    /**
     * Absolute zero. Represents origin.
     */
    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    /**
     * Positive X unit vector.
     */
    public static final Vector3 POSITIVE_X = new Vector3(1, 0, 0);

    /**
     * Positive Y unit vector.
     */
    public static final Vector3 POSITIVE_Y = new Vector3(0, 1, 0);

    /**
     * Positive Z unit vector.
     */
    public static final Vector3 POSITIVE_Z = new Vector3(0, 0, 1);

    /**
     * Negative X unit vector.
     */
    public static final Vector3 NEGATIVE_X = new Vector3(-1, 0, 0);

    /**
     * Negative Y unit vector.
     */
    public static final Vector3 NEGATIVE_Y = new Vector3(0, -1, 0);

    /**
     * Negative Z unit vector.
     */
    public static final Vector3 NEGATIVE_Z = new Vector3(0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x X value of this vector
     * @param y Y value of this vector
     * @param z Z value of this vector
     */
    public Vector3(double x, double y, double z) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    //
    // Variables
    //

    private final double x;
    private final double y;
    private final double z;

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
     * Gets the Z value of this vector.
     *
     * @return Z value
     */
    public double z() {
        return z;
    }

    /**
     * Gets the magnitude of this vector.
     * This operation includes {@link Math#sqrt(double)}.
     *
     * @return Magnitude
     */
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Gets the squared magnitude of this vector.
     *
     * @return Squared magnitude
     */
    public double magnitude2() {
        return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
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
    public Vector3 add(double s) {
        return new Vector3(x + s, y + s, z + s);
    }

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s Scalar to subtract
     * @return Resulting vector
     */
    @Nonnull
    public Vector3 subtract(double s) {
        return new Vector3(x - s, y - s, z - s);
    }

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s Scalar to multiply with
     * @return Resulting vector
     */
    @Nonnull
    public Vector3 multiply(double s) {
        return new Vector3(x * s, y * s, z * s);
    }

    /**
     * Divides this vector by a scalar.
     *
     * @param s Scalar to divide by
     * @return Resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    public Vector3 divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");
        return new Vector3(x / s, y / s, z / s);
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
    public Vector3 add(@Nonnull Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v Vector to subtract
     * @return Resulting vector
     */
    @Nonnull
    public Vector3 subtract(@Nonnull Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Gets the dot product of {@code this} and {@code v}.
     *
     * @param v Vector to multiply with
     * @return Dot product of two vectors
     */
    public double dot(@Nonnull Vector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    /**
     * Gets the cross product of {@code this} and {@code v}.
     *
     * @param v Vector to multiply with
     * @return Cross product of two vectors
     */
    @Nonnull
    public Vector3 cross(@Nonnull Vector3 v) {
        return new Vector3(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
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
    public Vector3 negate() {
        return multiply(-1);
    }

    /**
     * Normalizes this vector to a unit vector.
     *
     * @return Normalized unit vector
     */
    @Nonnull
    public Vector3 normalize() {
        return multiply(Numbers.isqrt(magnitude2()));
    }

    /**
     * Gets the distance from {@code this} to {@code v}.
     *
     * @param v Vector to get distance to
     * @return Distance between two vectors
     */
    @Nonnegative
    public double distance(@Nonnull Vector3 v) {
        return subtract(v).magnitude();
    }

    /**
     * Gets the squared distance from {@code this} to {@code v}.
     *
     * @param v Vector to get distance to
     * @return Squared distance between two vectors
     */
    @Nonnegative
    public double distance2(@Nonnull Vector3 v) {
        return subtract(v).magnitude2();
    }

    /**
     * Converts this vector to a pure quaternion.
     *
     * @return Pure quaternion derived from {@code this}
     */
    @Nonnull
    public Quaternion quaternion() {
        return new Quaternion(0, this);
    }

    /**
     * Rotates this vector.
     *
     * @param rq Rotation quaternion to rotate by
     * @return Rotated vector
     */
    @Nonnull
    public Vector3 rotate(@Nonnull Quaternion rq) {
        return rq.multiply(quaternion()).multiply(rq.conjugate()).vector();
    }

    //
    // Comparison
    //

    /**
     * Checks for equality.
     *
     * @param obj Object to compare to
     * @return {@code true} if given object is a vector, and the component scalars are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector3 v)) return false;
        return x == v.x && y == v.y && z == v.z;
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
    public static Vector3 parseVector(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("Vector{")) throw new NumberFormatException("Given string is not a vector.");

        final String[] strings = s
                .replaceAll("Vector\\{", "")
                .replaceAll("}", "")
                .split(", ");

        final double[] values = {Double.NaN, Double.NaN, Double.NaN};

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a vector.");

            switch (split[0]) {
                case "x" -> values[0] = Double.parseDouble(split[1]);
                case "y" -> values[1] = Double.parseDouble(split[1]);
                case "z" -> values[2] = Double.parseDouble(split[1]);
            }
        });

        return new Vector3(values[0], values[1], values[2]);
    }

    /**
     * Serializes this vector to a string.
     *
     * @return Stringified vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
