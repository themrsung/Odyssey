package civitas.celestis.object;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>AbstractBaseObject</h2>
 * <p>A base object with default behavior.</p>
 */
public abstract class AbstractBaseObject implements BaseObject {
    /**
     * Creates a new abstract base object.
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param rotation Rotation of this object
     */
    public AbstractBaseObject(@Nonnull UUID uniqueId, @Nonnull Vector location, @Nonnull Quaternion rotation) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.rotation = rotation;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Vector location;
    @Nonnull
    private Quaternion rotation;

    @Override
    public void tick(@Nonnull Duration delta) {}

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Vector getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    @Override
    public void setLocation(@Nonnull Vector location) {
        this.location = location;
    }

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    @Override
    public void move(@Nonnull Vector amount) {
        location = location.add(amount);
    }

    @Override
    public void rotate(@Nonnull Quaternion rotation) {
        this.rotation = rotation.multiply(this.rotation);
    }
}
