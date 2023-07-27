package civitas.celestis.geometry;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>Vertex</h2>
 * <p>Represents a renderable 3D triangle.</p>
 */
@Immutable
public class Vertex implements Iterable<Vector> {
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
     * Gets a list of points of this vertex.
     *
     * @return List of points
     */
    @Nonnull
    public List<Vector> points() {
        return List.of(a, b, c);
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

    /**
     * Gets the reflecting vector of given input vector.
     * Return value is not normalized.
     *
     * @param in Input vector
     * @return Reflection of input vector
     */
    @Nonnull
    public Vector reflection(@Nonnull Vector in) {
        return in.subtract(normal().multiply(2 * in.dot(normal())));
    }

    /**
     * Gets the reflecting ray of given ray.
     *
     * @param in Input ray
     * @return Reflection ray if this vertex intersects with given ray, {@code null} if not
     */
    @Nullable
    public Ray reflection(@Nonnull Ray in) {
        final Vector intersection = intersection(in);
        if (intersection == null) return null;

        final Vector reflection = reflection(in.direction());
        return new Ray(intersection, reflection);
    }

    /**
     * Gets the reflection ray of given light ray.
     *
     * @param in Input light ray
     * @return Reflection ray if this vertex intersects with given ray, {@code null} if not
     */
    @Nullable
    public LightRay reflection(@Nonnull LightRay in) {
        final Ray r = reflection((Ray) in);
        if (r == null) return null;

        return new LightRay(r, in.intensity());
    }

    /**
     * Transforms this vertex to relative space.
     *
     * @param origin   Relative origin
     * @param rotation Relative rotation
     * @return Relative vertex
     */
    @Nonnull
    public Vertex transform(@Nonnull Vector origin, @Nonnull Quaternion rotation) {
        return new Vertex(
                a.subtract(origin).rotate(rotation),
                b.subtract(origin).rotate(rotation),
                c.subtract(origin).rotate(rotation)
        );
    }

    /**
     * Inflates this vertex by given scale.
     *
     * @param scale Scale to use
     * @return Inflated vertex
     */
    @Nonnull
    public Vertex inflate(double scale) {
        return new Vertex(
                a.multiply(scale),
                b.multiply(scale),
                c.multiply(scale)
        );
    }

    /**
     * Called when this vertex is hit by a light ray.
     *
     * @param ray Ray this vertex was hit by
     */
    public void onLightRayHit(@Nonnull LightRay ray) {}

    /**
     * Gets the iterator of the three points of this vertex.
     *
     * @return Iterator of points
     */
    @Override
    public Iterator<Vector> iterator() {
        return List.of(a, b, c).iterator();
    }
}
