package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>Ray</h2>
 * <p>Represents a 3D ray.</p>
 *
 * @param origin    Origin of ray
 * @param direction Direction of ray
 */
public record Ray(
        @Nonnull Vector origin,
        @Nonnull Vector direction
) {
    /**
     * Creates a new ray. Direction is automatically normalized.
     *
     * @param origin    Origin of ray
     * @param direction Direction of ray
     */
    public Ray(@Nonnull Vector origin, @Nonnull Vector direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * Gets the destination of this ray.
     *
     * @param length Length of ray
     * @return Destination vector
     */
    @Nonnull
    public Vector destination(double length) {
        return origin.add(direction.multiply(length));
    }
}
