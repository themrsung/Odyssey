package civitas.celestis.geometry.vertex;

import civitas.celestis.geometry.ray.Ray;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

/**
 * <h2>Vertex</h2>
 * <p>A vertex represents a renderable 3D triangle.</p>
 */
public interface Vertex extends Iterable<Vector3> {
    /**
     * Gets point A of this vertex.
     *
     * @return Point A
     */
    @Nonnull
    Vector3 a();

    /**
     * Gets point B of this vertex.
     *
     * @return Point V
     */
    @Nonnull
    Vector3 b();

    /**
     * Gets point C of this vertex.
     *
     * @return Point C
     */
    @Nonnull
    Vector3 c();

    /**
     * Gets the current color of this vertex.
     *
     * @return Color
     */
    @Nonnull
    Color color();

    /**
     * Gets the reflection coefficient of this vector.
     * <p>
     * Higher values will result in reflecting light rays to keep more of its intensity.
     * Values over 1 will result in the reflecting light ray to become brighter.
     * </p>
     *
     * @return Reflection coefficient
     */
    @Nonnegative
    double reflectiveness();

    /**
     * Gets a list containing all points of this vertex.
     *
     * @return List of points
     */
    @Nonnull
    List<Vector3> points();

    /**
     * Gets the geometric centroid of this vertex.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Gets the surface normal of this vertex.
     *
     * @return Surface normal
     */
    @Nonnull
    Vector3 normal();

    /**
     * Gets the intersection between {@code this} and given ray.
     *
     * @param ray Ray to get intersection of
     * @return Intersection if found, {@code null} if not
     */
    @Nullable
    Vector3 intersection(@Nonnull Ray ray);

    /**
     * Inflates this vertex by given scale.
     *
     * @param scale Scale to inflate by
     * @return Inflated vertex
     */
    @Nonnull
    Vertex inflate(double scale);

    /**
     * Transforms this vertex to a relative coordinate system.
     *
     * @param origin   New origin
     * @param rotation Rotation to apply to all vectors
     * @return Transformed vertex
     */
    @Nonnull
    Vertex transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation);

    /**
     * Called when this vertex is hit by a ray.
     *
     * @param ray Ray this vertex was hit by
     */
    void onRayHit(@Nonnull Ray ray);
}
