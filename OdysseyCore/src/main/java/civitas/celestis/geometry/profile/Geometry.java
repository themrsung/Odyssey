package civitas.celestis.geometry.profile;

import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.object.BaseObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Geometry</h2>
 * <p>The geometric profile of an object.</p>
 */
public interface Geometry {
    /**
     * Gets the volume of this geometric profile.
     *
     * @return Volume
     */
    @Nonnegative
    double volume();

    /**
     * Gets the surface area of this geometric profile.
     *
     * @return Surface area
     */
    @Nonnegative
    double surfaceArea();

    /**
     * Generates a discrete solid in respect to given object.
     *
     * @param object Object using this geometric profile
     * @return Generated solid
     */
    @Nonnull
    Solid generate(@Nonnull BaseObject object);
}
