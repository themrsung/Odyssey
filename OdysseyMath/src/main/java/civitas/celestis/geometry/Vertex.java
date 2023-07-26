package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>Vertex</h2>
 * <p>
 * A renderable 3D triangle.
 * Calculations assume coordinates are counter-clockwise.
 * </p>
 */
public class Vertex implements Iterable<Vector> {
    /**
     * Creates a new vertex.
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     */
    public Vertex(@Nonnull Vector a, @Nonnull Vector b, @Nonnull Vector c) {
        this.a = a;
        this.b = b;
        this.c = c;
        color = Color.WHITE;
    }

    /**
     * Creates a new vertex.
     *
     * @param a     Point A
     * @param b     Point B
     * @param c     Point C
     * @param color Color of vertex
     */
    public Vertex(@Nonnull Vector a, @Nonnull Vector b, @Nonnull Vector c, @Nonnull Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    @Nonnull
    private final Vector a;
    @Nonnull
    private final Vector b;
    @Nonnull
    private final Vector c;
    @Nonnull
    private Color color;

    //
    // Getters
    //

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
     * @return Centroid
     */
    @Nonnull
    public Vector centroid() {
        return a.add(b).add(c).divide(3);
    }


    /**
     * Gets the surface normal of this vertex.
     * Return value is not normalized.
     *
     * @return Surface normal vector
     */
    @Nonnull
    public Vector surfaceNormal() {
        final Vector ab = b.subtract(a);
        final Vector ac = c.subtract(a);

        return ab.cross(ac);
    }

    /**
     * Gets the intersection between {@code this} and {@code ray}.
     *
     * @param ray Ray to check
     * @return Point of intersection if the two objects intersect, {@code null} if not
     */
    @Nullable
    public Vector intersection(@Nonnull Ray ray) {
        final Vector n = surfaceNormal();

        final double denominator = ray.direction().dot(n);
        if (denominator == 0) return null;

        final double length = (centroid().subtract(ray.origin()).dot(n)) / denominator;
        if (length < 0) return null;

        return ray.destination(length);
    }

    /**
     * Gets the current color of this vertex.
     *
     * @return Color
     */
    @Nonnull
    public Color color() {
        return color;
    }

    /**
     * Sets the color of this vertex.
     *
     * @param color Color to set to
     */
    public void color(@Nonnull Color color) {
        this.color = color;
    }

    //
    // Util
    //

    /**
     * Gets the iterator of the three points of this triangle.
     *
     * @return Iterator of points
     */
    @Override
    public Iterator<Vector> iterator() {
        return List.of(a, b, c).iterator();
    }
}
