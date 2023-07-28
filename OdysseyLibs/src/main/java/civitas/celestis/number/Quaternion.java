package civitas.celestis.number;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.Arrays;

@Immutable
public class Quaternion implements Serializable {
    //
    // Constants
    //

    /**
     * Absolute zero. Represents origin.
     */
    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);

    /**
     * Identity quaternion. Represents no rotation.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

    //
    // Constructors
    //

    /**
     * Creates a new quaternion.
     *
     * @param w W value of this quaternion
     * @param x X value of this quaternion
     * @param y Y value of this quaternion
     * @param z Z value of this quaternion
     */
    public Quaternion(double w, double x, double y, double z) {
        this.w = Numbers.requireFinite(w);
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Creates a new quaternion.
     *
     * @param s Scalar part of this quaternion
     * @param v Vector part of this quaternion
     */
    public Quaternion(double s, @Nonnull Vector3 v) {
        this(s, v.x(), v.y(), v.z());
    }

    //
    // Variables
    //
    private final double w;
    private final double x;
    private final double y;
    private final double z;

    //
    // Getters
    //

    /**
     * Gets the W value of this quaternion.
     *
     * @return W value
     */
    public double w() {
        return w;
    }

    /**
     * Gets the X value of this quaternion.
     *
     * @return X value
     */
    public double x() {
        return x;
    }

    /**
     * Gets the Y value of this quaternion.
     *
     * @return Y value
     */
    public double y() {
        return y;
    }

    /**
     * Gets the Z value of this quaternion.
     *
     * @return Z value
     */
    public double z() {
        return z;
    }

    /**
     * Gets the vector part of this quaternion.
     *
     * @return Vector part of quaternion
     */
    @Nonnull
    public Vector3 vector() {
        return new Vector3(x, y, z);
    }

    /**
     * Gets the magnitude of this quaternion.
     *
     * @return Magnitude
     */
    @Nonnegative
    public double magnitude() {
        return Math.sqrt(Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Gets the squared magnitude of this quaternion.
     *
     * @return Squared magnitude
     */
    @Nonnegative
    public double magnitude2() {
        return Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
    }

    //
    // Quaternion-Scalar Arithmetic
    //

    /**
     * Adds a scalar to this quaternion.
     *
     * @param s Scalar to add
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion add(double s) {
        return new Quaternion(w + s, x + s, y + s, z + s);
    }

    /**
     * Subtracts a scalar from this quaternion.
     *
     * @param s Scalar to subtract
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion subtract(double s) {
        return new Quaternion(w - s, x - s, y - s, z - s);
    }

    /**
     * Multiplies this quaternion by a scalar.
     *
     * @param s Scalar to multiply with
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion multiply(double s) {
        return new Quaternion(w * s, x * s, y * s, z * s);
    }

    /**
     * Divides this quaternion by a scalar.
     *
     * @param s Scalar to divide by
     * @return Resulting quaternion
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    public Quaternion divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");
        return new Quaternion(w / s, x / s, y / s, z / s);
    }

    //
    // Quaternion-Quaternion Arithmetic
    //

    /**
     * Adds another quaternion to this quaternion.
     *
     * @param q Quaternion to add
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion add(@Nonnull Quaternion q) {
        return new Quaternion(w + q.w, x + q.x, y + q.y, z + q.z);
    }

    /**
     * Subtracts another quaternion from this quaternion.
     *
     * @param q Quaternion to subtract
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion subtract(@Nonnull Quaternion q) {
        return new Quaternion(w - q.w, x - q.x, y - q.y, z - q.z);
    }

    /**
     * Multiplies this quaternion by another quaternion.
     *
     * @param q Quaternion to multiply with
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion multiply(@Nonnull Quaternion q) {
        return new Quaternion(
                (w * q.w) - vector().dot(q.vector()),
                q.vector().multiply(w).add(vector().multiply(q.w)).add(q.vector().cross(vector()))
        );
    }

    /**
     * Scales the rotation this quaternion represents.
     *
     * @param s Scalar to scale rotation to
     * @return Scaled quaternion
     */
    @Nonnull
    public Quaternion scale(double s) {
        // No need to scale identity quaternions
        if (w == 1) return IDENTITY;

        final double acos = Math.acos(w);
        return new Quaternion(
                Math.cos(acos * s),
                vector().divide(Math.sin(acos)).multiply(Math.sin(acos * s))
        );
    }

    //
    // Util
    //

    /**
     * Negates this quaternion.
     *
     * @return Negated quaternion
     */
    @Nonnull
    public Quaternion negate() {
        return multiply(-1);
    }

    /**
     * Normalizes this quaternion to a unit quaternion.
     *
     * @return Normalized unit quaternion
     */
    @Nonnull
    public Quaternion normalize() {
        return multiply(Numbers.isqrt(magnitude2()));
    }

    /**
     * Gets the conjugate of this quaternion.
     *
     * @return Conjugate
     */
    @Nonnull
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Gets the inverse of this quaternion.
     * If the inverse cannot be derived, this will return {@link Quaternion#ZERO}.
     *
     * @return Inverse
     */
    @Nonnull
    public Quaternion inverse() {
        try {
            return conjugate().multiply(Numbers.isqrt(magnitude2()));
        } catch (ArithmeticException e) {
            return ZERO;
        }
    }

    /**
     * Checks for equality.
     *
     * @param obj Object to compare to
     * @return {@code true} if given object is a quaternion, and the component scalars are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Quaternion q)) return false;
        return w == q.w && x == q.x && y == q.y && z == q.z;
    }

    /**
     * Deserializes a string to a quaternion.
     *
     * @param s String to deserialize
     * @return Deserialized quaternion
     * @throws NumberFormatException When the string is not parsable to a quaternion
     */
    @Nonnull
    public static Quaternion parseQuaternion(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("Quaternion{")) throw new NumberFormatException("Given string is not a quaternion.");

        final String[] strings = s
                .replaceAll("Quaternion\\{", "")
                .replaceAll("}", "")
                .split(", ");

        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a quaternion.");

            switch (split[0]) {
                case "w" -> values[0] = Double.parseDouble(split[1]);
                case "x" -> values[1] = Double.parseDouble(split[1]);
                case "y" -> values[2] = Double.parseDouble(split[1]);
                case "z" -> values[3] = Double.parseDouble(split[1]);
            }
        });

        return new Quaternion(values[0], values[1], values[2], values[3]);
    }

    /**
     * Serializes this quaternion into a string.
     *
     * @return Stringified quaternion
     */
    @Override
    @Nonnull
    public String toString() {
        return "Quaternion{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
