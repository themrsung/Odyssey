package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>Vertex</h2>
 * <p>Represents a renderable 3D triangle.</p>
 */
@Immutable
public class Vertex {
    /**
     * Creates a new vertex.
     *
     * @param a Point A of this vertex
     * @param b Point B of this vertex
     * @param c Point C of this vertex
     */
    public Vertex(@Nonnull Vector a, @Nonnull Vector b, @Nonnull Vector c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Copy constructor.
     *
     * @param other Vertex to copy
     */
    public Vertex(@Nonnull Vertex other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    @Nonnull
    private final Vector a;
    @Nonnull
    private final Vector b;
    @Nonnull
    private final Vector c;

    /**
     * Gets point A of this vertex.
     *
     * @return Point A
     */
    @Nonnull
    public Vector a() {
        return a;
    }

    /**
     * Gets point B of this vertex.
     *
     * @return Point B
     */
    @Nonnull
    public Vector b() {
        return b;
    }

    /**
     * Gets point C of this vertex.
     *
     * @return Point C
     */
    @Nonnull
    public Vector c() {
        return c;
    }

    /**
     * Gets the geometric centroid of this vertex.
     *
     * @return Geometric centroid
     */
    @Nonnull
    public Vector centroid() {
        return a.add(b).add(c).divide(3);
    }

    /**
     * Gets the surface normal of this vertex.
     *
     * @return Surface normal
     */
    @Nonnull
    public Vector normal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    /**
     * Gets the intersection between {@code this} and given ray.
     *
     * @param ray Ray to check
     * @return Intersection if found, {@code null} if not
     */
    @Nullable
    public Vector intersection(@Nonnull Ray ray) {
        final Vector n = normal();

        final double denominator = ray.direction().dot(n);
        if (denominator == 0) return null;

        final double length = (centroid().subtract(ray.origin()).dot(n)) / denominator;
        if (length < 0) return null;

        return ray.destination(length);
    }
}
