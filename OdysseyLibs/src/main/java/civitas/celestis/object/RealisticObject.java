package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>RealisticObject</h2>
 * <p>The default implementation of {@link TangibleObject}.</p>
 */
public class RealisticObject extends AbstractObject implements TangibleObject {
    /**
     * Creates a new object.
     *
     * @param uniqueId Unique identifier of this object
     * @param location Location of this object
     * @param geometry Geometric profile of this object
     * @param mass     Mass of this object
     */
    public RealisticObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Geometry geometry,
            @Nonnegative double mass
    ) {
        super(uniqueId, location);
        this.geometry = geometry;
        this.mass = mass;
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
    public RealisticObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Quaternion rotation,
            @Nonnull Geometry geometry,
            @Nonnegative double mass
    ) {
        super(uniqueId, location, rotation);
        this.geometry = geometry;
        this.mass = mass;
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
    public RealisticObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate,
            @Nonnull Geometry geometry,
            @Nonnegative double mass
    ) {
        super(uniqueId, location, acceleration, rotation, rotationRate);
        this.geometry = geometry;
        this.mass = mass;
    }

    @Nonnull
    private Geometry geometry;
    @Nonnegative
    private double mass;

    @Override
    @Nonnull
    public Geometry getGeometry() {
        return geometry;
    }

    @Nonnull
    @Override
    public Solid getSolid() {
        return geometry.build(this);
    }

    @Override
    @Nonnegative
    public double getMass() {
        return mass;
    }

    @Override
    @Nonnegative
    public double getVolume() {
        return geometry.volume();
    }

    @Override
    @Nonnegative
    public double getDensity() {
        if (mass == 0) return 0;
        return getVolume() / mass;
    }

    @Override
    @Nonnegative
    public double getDragCoefficient() {
        return getSolid().dragCoefficient(getAcceleration().negate());
    }

    @Override
    @Nonnegative
    public double getCrossSection() {
        return getSolid().crossSection(getAcceleration().negate());
    }

    @Override
    public boolean overlaps(@Nonnull Solid solid) {
        return getSolid().overlaps(solid);
    }

    @Override
    public boolean overlaps(@Nonnull TangibleObject other) {
        return getSolid().overlaps(other.getSolid());
    }

    @Override
    public void setGeometry(@Nonnull Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public void setMass(@Nonnegative double mass) {
        this.mass = mass;
    }
}
