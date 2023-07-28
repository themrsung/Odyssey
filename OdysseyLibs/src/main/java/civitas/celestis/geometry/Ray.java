package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

    /**
     * Gets the reflection ray when collided with given surface.
     * If the intersection cannot found, this will return {@code null}.
     *
     * @param surface Surface to reflect off of
     * @return Reflection ray of {@code this}
     */
    @Nullable
    Ray reflection(@Nonnull Vertex surface);
}
