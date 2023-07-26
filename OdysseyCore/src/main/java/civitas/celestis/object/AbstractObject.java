package civitas.celestis.object;

import civitas.celestis.geometry.discrete.Solid;
import civitas.celestis.geometry.relative.Geometry;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>An adapter class for {@link OdysseyObject}.</p>
 */
public abstract class AbstractObject implements OdysseyObject {
    /**
     * All-args constructor.
     *
     * @param uniqueId     Unique identifier of this object
     * @param world        World of this object
     * @param geometry     Geometric profile of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     */
    public AbstractObject(
            @Nonnull UUID uniqueId,
            @Nullable World world,
            @Nonnull Geometry geometry,
            @Nonnull Vector location,
            @Nonnull Vector acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate
    ) {
        this.uniqueId = uniqueId;
        this.world = world;
        this.geometry = geometry;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nullable
    private World world;
    @Nonnull
    private Geometry geometry;
    @Nonnull
    private Vector location;
    @Nonnull
    private Vector acceleration;
    @Nonnull
    private Quaternion rotation;
    @Nonnull
    private Quaternion rotationRate;

    @Override
    public void tick(@Nonnull Duration delta) {
        final double seconds = delta.getMillis() / 1000d;

        // Apply acceleration and rate of rotation
        move(acceleration.multiply(seconds));
        rotate(rotationRate.scale(seconds));
    }

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nullable
    public World getWorld() {
        return world;
    }

    @Override
    @Nonnull
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    @Nonnull
    public Vector getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector getAcceleration() {
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
    public double getVelocity() {
        return acceleration.magnitude();
    }

    @Nonnull
    @Override
    public Solid getSolid() {
        return geometry.build(location, rotation);
    }

    @Override
    public double getMass() {
        return geometry.mass();
    }

    @Override
    public double getVolume() {
        return geometry.volume();
    }

    @Override
    public double getDensity() {
        return geometry.density();
    }

    @Override
    public double getDragCoefficient() {
        return getSolid().dragCoefficient(acceleration.negate());
    }

    @Override
    public double getCrossSection() {
        return getSolid().crossSection(acceleration.negate());
    }

    @Override
    public void setWorld(@Nullable World world) {
        this.world = world;
    }

    @Override
    public void setGeometry(@Nonnull Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public void setLocation(@Nonnull Vector location) {
        this.location = location;
    }

    @Override
    public void setAcceleration(@Nonnull Vector acceleration) {
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
    public void move(@Nonnull Vector amount) {
        setLocation(location.add(amount));
    }

    @Override
    public void accelerate(@Nonnull Vector amount) {
        setAcceleration(acceleration.add(amount));
    }

    @Override
    public void rotate(@Nonnull Quaternion rotation) {
        setRotation(rotation.multiply(this.rotation));
    }

    @Override
    public boolean overlaps(@Nonnull Solid solid) {
        return getSolid().overlaps(solid);
    }

    @Override
    public boolean overlaps(@Nonnull OdysseyObject other) {
        return getSolid().overlaps(other.getSolid());
    }
}
