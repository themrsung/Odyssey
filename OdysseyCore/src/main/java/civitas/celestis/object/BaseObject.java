package civitas.celestis.object;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>BaseObject</h2>
 * <p>An object which can be placed in a world.</p>
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
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Moves this object.
     *
     * @param amount Amount to move
     */
    void move(@Nonnull Vector amount);

    /**
     * Rotates this object.
     *
     * @param rotation Rotation to apply
     */
    void rotate(@Nonnull Quaternion rotation);

    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);
}
