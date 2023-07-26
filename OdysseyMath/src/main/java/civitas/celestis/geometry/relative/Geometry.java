package civitas.celestis.geometry.relative;

import civitas.celestis.geometry.discrete.Solid;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Geometry</h2>
 * <p>The geometric profile of an object.</p>
 */
public interface Geometry {
    /**
     * Gets the mass of this geometric profile.
     *
     * @return Mass
     */
    @Nonnegative
    double mass();

    /**
     * Gets the volume of this geometric profile.
     *
     * @return Volume
     */
    @Nonnegative
    double volume();

    /**
     * Gets the density of this geometric profile.
     *
     * @return Density
     */
    @Nonnegative
    double density();

    /**
     * Builds this geometric profile into a discrete solid.
     *
     * @param location Location to use
     * @param rotation Rotation to use
     * @return Built solid
     */
    @Nonnull
    Solid build(@Nonnull Vector location, @Nonnull Quaternion rotation);
}
