package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>Ray</h2>
 * <p>Represents a directional line in 3D.</p>
 */
public interface Ray {
    /**
     * Gets the origin of this ray.
     *
     * @return Origin
     */
    @Nonnull
    Vector origin();

    /**
     * Gets the direction of this ray.
     *
     * @return Direction
     */
    @Nonnull
    Vector direction();

    /**
     * Gets the destination of this ray.
     *
     * @param length Length of ray
     * @return Destination
     */
    @Nonnull
    Vector destination(double length);
}
