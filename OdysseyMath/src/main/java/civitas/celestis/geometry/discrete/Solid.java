package civitas.celestis.geometry.discrete;

import civitas.celestis.geometry.Vertex;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>A discrete solid which represents a physical area of 3D space.</p>
 */
public interface Solid {
    /**
     * Gets the geometric centroid of this solid.
     *
     * @return Centroid
     */
    @Nonnull
    Vector centroid();

    /**
     * Gets the rotation of this solid.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion rotation();

    /**
     * Gets the volume of this solid.
     *
     * @return Volume
     */
    @Nonnegative
    double volume();

    /**
     * Gets the coefficient of drag when viewed from given direction.
     *
     * @param direction Direction to view this solid from
     * @return Coefficient of drag
     */
    @Nonnegative
    double dragCoefficient(@Nonnull Vector direction);

    /**
     * Gets the cross-section when viewed from given direction.
     *
     * @param direction Direction to view this solid from
     * @return Cross-section
     */
    @Nonnegative
    double crossSection(@Nonnull Vector direction);

    /**
     * Gets a list of corners of this solid.
     *
     * @return List of corners
     */
    @Nonnull
    List<Vector> getCorners();

    /**
     * Gets a list of vertices of this solid.
     *
     * @return List of vertices
     */
    @Nonnull
    List<Vertex> getVertices();

    /**
     * Checks if this solid contains given point.
     *
     * @param point Point to check
     * @return {@code true} if this solid contains given point
     */
    boolean contains(@Nonnull Vector point);

    /**
     * Checks if this solid overlaps another.
     *
     * @param other Solid to check
     * @return {@code true} if the two solids overlap
     */
    boolean overlaps(@Nonnull Solid other);
}
