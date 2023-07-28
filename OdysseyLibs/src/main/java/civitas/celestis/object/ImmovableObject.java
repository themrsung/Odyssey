package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>ImmovableObject</h2>
 * <p>
 * An object where the location and rotation cannot be changed after creation.
 * Immovable objects are still subject to physics, apart from the fact that they are immovable.
 * (they can still collide with normal objects)
 * </p>
 * <p>
 * While acceleration and rate of rotation can be changed, it will only effect the counterparty of a collision.
 * </p>
 */
public class ImmovableObject extends RealisticObject {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param geometry Geometric profile of this object
     * @param mass     Mass of this object
     */
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Geometry geometry, @Nonnegative double mass) {
        super(uniqueId, location, geometry, mass);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     * @param geometry Geometric profile of this object
     * @param mass     Mass of this object
     */
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Quaternion rotation, @Nonnull Geometry geometry, @Nonnegative double mass) {
        super(uniqueId, location, rotation, geometry, mass);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId     Unique identifier of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     * @param geometry     Geometric profile of this object
     * @param mass         Mass of this object
     */
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Vector3 acceleration, @Nonnull Quaternion rotation, @Nonnull Quaternion rotationRate, @Nonnull Geometry geometry, @Nonnegative double mass) {
        super(uniqueId, location, acceleration, rotation, rotationRate, geometry, mass);
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {}

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {}
}
