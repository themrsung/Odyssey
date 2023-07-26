package civitas.celestis.object;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.util.Identifiable;

import javax.annotation.Nonnull;

/**
 * <h2>OdysseyObject</h2>
 * <p>An object used in Odyssey.</p>
 */
public interface OdysseyObject extends Identifiable {
    //
    // Location & Rotation
    //

    /**
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector getAcceleration();

    /**
     * Gets the rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the rate of rotation of this object.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    //
    // Movement & Rotation
    //

    /**
     * Moves this object by given amount.
     * @param amount Amount to move
     */
    void move(@Nonnull Vector amount);

    /**
     * Accelerates this object by given acceleration.
     * @param acceleration Acceleration to apply
     */
    void acceleration(@Nonnull Vector acceleration);

    /**
     * Rotates this object by given rotation.
     * @param rotation Rotation to apply
     */
    void rotate(@Nonnull Quaternion rotation);

    /**
     * Adds rotation rate to this object.
     * @param rate Rate of rotation to add
     */
    void rotateRate(@Nonnull Quaternion rate);
}
