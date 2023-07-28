package civitas.celestis.object;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>The default implementation of {@link BaseObject}.</p>
 */
public abstract class AbstractObject implements BaseObject {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object.
     * @param location Location of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location) {
        this(uniqueId, location, Quaternion.IDENTITY);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Quaternion rotation) {
        this(uniqueId, location, Vector3.ZERO, rotation, Quaternion.IDENTITY);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId     Unique identifier of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     */
    public AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate
    ) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Vector3 location;
    @Nonnull
    private Vector3 acceleration;
    @Nonnull
    private Quaternion rotation;
    @Nonnull
    private Quaternion rotationRate;

    @Override
    public void tick(@Nonnull Duration delta) {
        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Apply acceleration
        move(acceleration.multiply(seconds));

        // Apply rate of rotation
        rotate(rotationRate.scale(seconds));
    }

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
    }

    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {
        this.rotationRate = rotationRate;
    }

    @Override
    public void move(@Nonnull Vector3 amount) {
        this.location = location.add(amount);
    }

    @Override
    public void accelerate(@Nonnull Vector3 amount) {
        this.acceleration = acceleration.add(amount);
    }

    @Override
    public void rotate(@Nonnull Quaternion amount) {
        this.rotation = amount.multiply(rotation);
    }

    @Override
    public void rotateRate(@Nonnull Quaternion amount) {
        this.rotationRate = amount.multiply(rotationRate);
    }
}
