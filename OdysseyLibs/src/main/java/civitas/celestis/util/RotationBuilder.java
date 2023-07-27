package civitas.celestis.util;

import civitas.celestis.number.Numbers;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>RotationBuilder</h2>
 * <p>A builder class for creating rotation quaternions.</p>
 */
public final class RotationBuilder {
    /**
     * Obtains a builder instance with given axis/angle.
     *
     * @param axis  Axis of rotation
     * @param angle Angle of rotation in radians
     * @return Builder instance
     */
    @Nonnull
    public static RotationBuilder fromAxisAngle(@Nonnull Vector axis, double angle) {
        return new RotationBuilder(axis, angle);
    }

    /**
     * Creates a rotation builder from an existing rotation quaternion.
     *
     * @param rotation Rotation quaternion
     */
    public RotationBuilder(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    /**
     * Creates a rotation builder from an axis/angle notation.
     * Rotation follows ths right-hand rule.
     *
     * @param axis  Axis of rotation
     * @param angle Angle of rotation in radians
     */
    public RotationBuilder(@Nonnull Vector axis, double angle) {
        if (Numbers.requireFinite(angle) == 0) {
            this.rotation = Quaternion.IDENTITY;
        } else {
            this.rotation = new Quaternion(Math.cos(angle / 2), axis.normalize().multiply(Math.sin(angle / 2)));
        }
    }

    /**
     * Creates a rotation builder with no rotation.
     */
    public RotationBuilder() {
        this(Quaternion.IDENTITY);
    }

    @Nonnull
    private Quaternion rotation;

    /**
     * Adds rotation to this builder.
     *
     * @param rq Rotation quaternion to add
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder rotate(@Nonnull Quaternion rq) {
        this.rotation = rq.multiply(rotation);
        return this;
    }

    /**
     * Adds rotation to this builder.
     *
     * @param axis  Axis of rotation
     * @param angle Angle of rotation in radians
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder rotate(@Nonnull Vector axis, double angle) {
        if (Numbers.requireFinite(angle) == 0) {
            return rotate(Quaternion.IDENTITY);
        } else {
            return rotate(new Quaternion(Math.cos(angle / 2), axis.normalize().multiply(Math.sin(angle / 2))));
        }
    }

    /**
     * Adds yaw to this builder.
     *
     * @param radians Yaw in radians
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addYaw(double radians) {
        return rotate(Vector.POSITIVE_Y, Numbers.requireFinite(radians));
    }

    /**
     * Adds yaw to this builder.
     *
     * @param degrees Yaw in degrees
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addYawDegrees(double degrees) {
        return addYaw(Math.toRadians(degrees));
    }

    /**
     * Adds pitch to this builder.
     *
     * @param radians Pitch in radians
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addPitch(double radians) {
        return rotate(Vector.POSITIVE_X, Numbers.requireFinite(radians));
    }

    /**
     * Adds pitch to this builder.
     *
     * @param degrees Pitch in degrees
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addPitchDegrees(double degrees) {
        return addPitch(Math.toRadians(degrees));
    }

    /**
     * Adds roll to this builder.
     *
     * @param radians Roll in radians
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addRoll(double radians) {
        return rotate(Vector.POSITIVE_Z, Numbers.requireFinite(radians));
    }

    /**
     * Adds roll to this builder.
     *
     * @param degrees Roll in degrees
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addRollDegrees(double degrees) {
        return addRoll(Math.toRadians(degrees));
    }

    /**
     * Builds the rotation quaternion.
     *
     * @return Built rotation quaternion
     */
    @Nonnull
    public Quaternion build() {
        return rotation;
    }
}
