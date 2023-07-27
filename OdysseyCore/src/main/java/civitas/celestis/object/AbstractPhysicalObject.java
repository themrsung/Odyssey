package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>AbstractPhysicalObject</h2>
 * <p>An abstract object with physical properties.</p>
 */
public abstract class AbstractPhysicalObject extends AbstractBaseObject implements PhysicalObject {
    /**
     * Creates a new abstract physical object.
     *
     * @param uniqueId     Unique identifier of this object.
     * @param location     Location of this object
     * @param rotation     Rotation of this object
     * @param mass         Mass of this object
     * @param geometry     Geometric profile of this object
     * @param acceleration Acceleration of this object
     * @param rotationRate Rate of rotation of this object
     */
    public AbstractPhysicalObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector location,
            @Nonnull Quaternion rotation,
            @Nonnegative double mass,
            @Nonnull Geometry geometry,
            @Nonnull Vector acceleration,
            @Nonnull Quaternion rotationRate
    ) {
        super(uniqueId, location, rotation);
        this.mass = mass;
        this.geometry = geometry;
        this.acceleration = acceleration;
        this.rotationRate = rotationRate;
    }

    @Nonnegative
    private double mass;
    @Nonnull
    private Geometry geometry;
    @Nonnull
    private Vector acceleration;
    @Nonnull
    private Quaternion rotationRate;

    @Override
    public void tick(@Nonnull Duration delta) {
        super.tick(delta);

        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Apply acceleration & rate of rotation
        move(acceleration.multiply(seconds));
        rotate(rotationRate.scale(seconds));
    }

    @Override
    @Nonnegative
    public double getMass() {
        return mass;
    }

    @Override
    @Nonnegative
    public double getDensity() {
        final double v = geometry.volume();
        if (v == 0) return 0;

        return mass / v;
    }

    @Override
    @Nonnull
    public Geometry getGeometry() {
        return geometry;
    }

    @Nonnull
    @Override
    public Solid getSolid() {
        return geometry.generate(this);
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
    public boolean overlaps(@Nonnull PhysicalObject other) {
        return getSolid().overlaps(other.getSolid());
    }

    @Override
    public void setMass(@Nonnegative double mass) {
        this.mass = mass;
    }

    @Override
    public void setGeometry(@Nonnull Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    @Nonnull
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    @Nonnegative
    public double getVelocity() {
        return acceleration.magnitude();
    }

    @Override
    @Nonnegative
    public double getVelocity2() {
        return acceleration.magnitude2();
    }

    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    @Override
    public void setAcceleration(@Nonnull Vector acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {
        this.rotationRate = rotationRate;
    }

    @Override
    public void accelerate(@Nonnull Vector amount) {
        acceleration = acceleration.add(amount);
    }

    @Override
    public void rotateRate(@Nonnull Quaternion rate) {
        rotationRate = rate.multiply(rotationRate);
    }
}
