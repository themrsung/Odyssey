package civitas.celestis.object;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>BaseObject</h2>
 * <p>A basic object.</p>
 */
public interface BaseObject {
    /**
     * Gets the unique identifier of this object.
     *
     * @return Unique identifier
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    /**
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Gets the acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector3 getAcceleration();

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
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector3 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rotationRate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rotationRate);

    /**
     * Moves this object by given amount.
     *
     * @param amount Amount to move
     */
    void move(@Nonnull Vector3 amount);

    /**
     * Accelerates this object by given amount.
     *
     * @param amount Acceleration to apply
     */
    void accelerate(@Nonnull Vector3 amount);

    /**
     * Rotates this object by given amount.
     *
     * @param amount Rotation to apply
     */
    void rotate(@Nonnull Quaternion amount);

    /**
     * Changes the rate of rotation of this object by given amount.
     *
     * @param amount Rate of rotation to apply
     */
    void rotateRate(@Nonnull Quaternion amount);
}
