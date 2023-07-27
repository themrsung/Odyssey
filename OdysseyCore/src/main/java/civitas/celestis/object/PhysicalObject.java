package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>PhysicalObject</h2>
 * <p>A physical object is subject to physics.</p>
 */
public interface PhysicalObject extends BaseObject {
    //
    // Properties
    //

    /**
     * Gets the mass of this object.
     *
     * @return Mass
     */
    @Nonnegative
    double getMass();

    /**
     * Gets the density of this object.
     *
     * @return Density
     */
    @Nonnegative
    double getDensity();

    /**
     * Gets the geometric profile of this object.
     *
     * @return Geometric profile
     */
    @Nonnull
    Geometry getGeometry();

    /**
     * Gets the discrete solid of this object.
     *
     * @return Discrete solid
     */
    @Nonnull
    Solid getSolid();

    /**
     * Gets the current coefficient of drag of this object.
     *
     * @return Coefficient of drag
     */
    @Nonnegative
    double getDragCoefficient();

    /**
     * Gets the current cross-section of this object.
     *
     * @return Cross-section
     */
    @Nonnegative
    double getCrossSection();

    /**
     * Checks if this object overlaps another.
     *
     * @param other Object to check
     * @return {@code true} if the objects overlap
     */
    boolean overlaps(@Nonnull PhysicalObject other);

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass
     */
    void setMass(@Nonnegative double mass);

    /**
     * Sets the geometric profile of this object.
     *
     * @param geometry Geometric profile
     */
    void setGeometry(@Nonnull Geometry geometry);

    //
    // Acceleration & Rotation
    //

    /**
     * Gets the acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector getAcceleration();

    /**
     * Gets the velocity of this object.
     * @return Velocity
     */
    @Nonnegative
    double getVelocity();

    /**
     * Gets the squared velocity of this object.
     * @return Squared velocity
     */
    @Nonnegative
    double getVelocity2();

    /**
     * Gets the rate of rotation of this object.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Set the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    /**
     * Accelerates this object by given amount.
     *
     * @param amount Acceleration to apply
     */
    void accelerate(@Nonnull Vector amount);

    /**
     * Changes the rate of rotation of this object.
     *
     * @param rate Rate of rotation to apply
     */
    void rotateRate(@Nonnull Quaternion rate);
}
