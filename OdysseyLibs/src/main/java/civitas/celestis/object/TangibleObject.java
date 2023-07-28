package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.geometry.solid.Solid;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>TangibleObject</h2>
 * <p>Tangible objects have a geometric profile and are subject to physics.</p>
 */
public interface TangibleObject extends BaseObject {
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
     * Gets the mass of this object.
     *
     * @return Mass of object
     */
    @Nonnegative
    double getMass();

    /**
     * Gets the volume of this object.
     *
     * @return Volume
     */
    @Nonnegative
    double getVolume();

    /**
     * Gets the density of this object.
     *
     * @return Density
     */
    @Nonnegative
    double getDensity();

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
     * Checks if this object overlaps another solid.
     *
     * @param solid Solid to check
     * @return {@code true} if this object overlaps the solid
     */
    boolean overlaps(@Nonnull Solid solid);

    /**
     * Checks if this object overlaps another object.
     *
     * @param other Object to check
     * @return {@code true} if the two objects overlap
     */
    boolean overlaps(@Nonnull TangibleObject other);

    /**
     * Sets the geometric profile of this object.
     *
     * @param geometry Geometric profile
     */
    void setGeometry(@Nonnull Geometry geometry);

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass of object
     */
    void setMass(@Nonnegative double mass);
}
