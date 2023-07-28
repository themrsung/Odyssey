package civitas.celestis.geometry.profile;

import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.object.TangibleObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Geometry</h2>
 * <p>
 * A geometric profile.
 * Geometric profiles are used as blueprints to store the potential solid of an object.
 * Solids are built real-time using {@link Geometry#build(TangibleObject)}.
 * </p>
 */
public interface Geometry {
    /**
     * Gets the volume of this geometric profile.
     * This does not require the building of a discrete solid.
     *
     * @return Volume of geometry
     */
    @Nonnegative
    double volume();

    /**
     * Builds the discrete solid of this geometric profile.
     *
     * @param object Object to build solid as
     * @return Built solid
     */
    @Nonnull
    Solid build(@Nonnull TangibleObject object);
}
