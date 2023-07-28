package civitas.celestis.geometry.solid;

import civitas.celestis.geometry.vertex.Vertex;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>Represents a three-dimensional shape.</p>
 */
public interface Solid {
    //
    // Graphics
    //

    /**
     * Gets a list of vertices which can be rendered to a scene.
     *
     * @return List of vertices
     */
    @Nonnull
    List<Vertex> vertices();

    //
    // Geometry
    //

    /**
     * Gets the geometric centroid of this solid.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Gets the rotation of this solid.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion rotation();

    /**
     * Gets a list of corners of this solid.
     *
     * @return List of corners
     */
    @Nonnull
    List<Vector3> corners();

    /**
     * Checks if this solid contains given point.
     *
     * @param point Point to check
     * @return {@code true} if given point is within the bounds of {@code this}
     */
    boolean contains(@Nonnull Vector3 point);

    /**
     * Checks if this solid overlaps another.
     *
     * @param other Solid to check
     * @return {@code true} if ths two solids overlap
     */
    boolean overlaps(@Nonnull Solid other);

    /**
     * Gets the volume of this solid.
     *
     * @return Volume
     */
    @Nonnegative
    double volume();

    /**
     * Gets the surface area of this solid.
     *
     * @return Surface area
     */
    @Nonnegative
    double surfaceArea();

    //
    // Physics
    //

    /**
     * Gets the coefficient of drag of this solid when viewed from given angle.
     *
     * @param direction Direction to view this solid from
     * @return Coefficient of drag
     */
    @Nonnegative
    double dragCoefficient(@Nonnull Vector3 direction);

    /**
     * Gets the cross-section of this solid when viewed from given angle.
     *
     * @param direction Direction to view this solid from
     * @return Cross-section
     */
    @Nonnegative
    double crossSection(@Nonnull Vector3 direction);
}
