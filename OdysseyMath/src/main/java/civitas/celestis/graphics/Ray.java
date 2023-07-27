package civitas.celestis.graphics;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>Ray</h2>
 * <p>Represents a 3D ray.</p>
 */
@Immutable
public class Ray {
    /**
     * Creates a new ray. Direction is automatically normalized.
     *
     * @param origin      Origin of ray
     * @param direction   Direction of ray
     * @param reflections Amount of reflections remaining before this ray terminates
     */
    public Ray(@Nonnull Vector origin, @Nonnull Vector direction, @Nonnegative long reflections) {
        this.origin = origin;
        this.direction = direction.normalize();
        this.reflections = reflections;
    }

    /**
     * Default copy constructor.
     *
     * @param other Ray to copy
     */
    public Ray(@Nonnull Ray other) {
        this.origin = other.origin;
        this.direction = other.direction;
        this.reflections = other.reflections;
    }

    @Nonnull
    private final Vector origin;
    @Nonnull
    private final Vector direction;
    @Nonnegative
    private final long reflections;

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
     * @return Destination vector
     */
    @Nonnull
    public Vector destination(double length) {
        return origin.add(direction.multiply(length));
    }

    /**
     * Gets the amount of reflections remaining before this ray terminates.
     *
     * @return Amount of reflections remaining
     */
    @Nonnegative
    public long reflections() {
        return reflections;
    }

    /**
     * Returns a reflected ray of {@code this}
     *
     * @param destination   Destination of ray
     * @param surfaceNormal Surface normal of reflection surface
     * @return Reflected ray
     */
    @Nonnull
    public Ray reflect(@Nonnull Vector destination, @Nonnull Vector surfaceNormal) {
        return new Ray(
                destination,
                direction.subtract(surfaceNormal.multiply(2).cross(direction.subtract(surfaceNormal))),
                reflections - 1
        );
    }
}
