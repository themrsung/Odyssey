package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

/**
 * <h2>Vertex</h2>
 * <p>A vertex represents a renderable 3D triangle.</p>
 */
public interface Vertex extends Iterable<Vector> {
    /**
     * Gets point A of this vertex.
     *
     * @return Point A
     */
    @Nonnull
    Vector a();

    /**
     * Gets point B of this vertex.
     *
     * @return Point V
     */
    @Nonnull
    Vector b();

    /**
     * Gets point C of this vertex.
     *
     * @return Point C
     */
    @Nonnull
    Vector c();

    /**
     * Gets the current color of this vertex.
     *
     * @return Color
     */
    @Nonnull
    Color color();

    /**
     * Gets a list containing all points of this vertex.
     *
     * @return List of points
     */
    @Nonnull
    List<Vector> points();

    /**
     * Gets the geometric centroid of this vertex.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector centroid();

    /**
     * Gets the surface normal of this vertex.
     *
     * @return Surface normal
     */
    @Nonnull
    Vector normal();

    /**
     * Gets the intersection between {@code this} and given ray.
     *
     * @param ray Ray to get intersection of
     * @return Intersection if found, {@code null} if not
     */
    @Nullable
    Vector intersection(@Nonnull Ray ray);

    /**
     * Gets the reflection of given ray.
     * If this vertex does not intersect with given ray, this will return {@code null}.
     *
     * @param in Input ray
     * @return Reflection of ray if derivable, {@code null} if not
     */
    @Nullable
    Ray reflection(@Nonnull Ray in);
}
