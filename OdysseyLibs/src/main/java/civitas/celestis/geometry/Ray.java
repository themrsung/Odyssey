package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>Ray</h2>
 * <p>Represents a ray.</p>
 */
@Immutable
public class Ray {
    /**
     * Creates a new ray.
     *
     * @param origin    Origin of this ray
     * @param direction Direction of this ray
     */
    public Ray(@Nonnull Vector origin, @Nonnull Vector direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * Copy constructor.
     *
     * @param other Ray to copy
     */
    public Ray(@Nonnull Ray other) {
        this.origin = other.origin;
        this.direction = other.direction;
    }

    @Nonnull
    private final Vector origin;
    @Nonnull
    private final Vector direction;

    /**
     * Gets the origin of this ray.
     *
     * @return Origin
     */
    @Nonnull
    public Vector origin() {
        return origin;
    }

    /**
     * Gets the direction of this ray.
     *
     * @return Direction
     */
    @Nonnull
    public Vector direction() {
        return direction;
    }

    /**
     * Gets the destination of this ray.
     *
     * @param length Length of ray
     * @return Destination
     */
    @Nonnull
    public Vector destination(double length) {
        return origin.add(direction.multiply(length));
    }

    /**
     * Converts this ray to a string.
     *
     * @return Stringified ray
     */
    @Override
    @Nonnull
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }
}
