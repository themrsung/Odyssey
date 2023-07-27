package civitas.celestis.geometry.solid;

import civitas.celestis.geometry.Vertex;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>A discrete solid used for physics and graphics calculations.</p>
 */
public interface Solid {
    /**
     * Checks if this solid contains given point.
     *
     * @param point Point to check
     * @return {@code true} if given point is within the bounds of this solid
     */
    boolean contains(@Nonnull Vector point);

    /**
     * Checks if this solid overlaps another.
     *
     * @param other Solid to check
     * @return {@code true} if the two solids overlap
     */
    boolean overlaps(@Nonnull Solid other);

    /**
     * Gets the coefficient of drag of this solid when viewed from given direction.
     *
     * @param direction Direction to view this solid from
     * @return Coefficient of drag
     */
    @Nonnegative
    double dragCoefficient(@Nonnull Vector direction);

    /**
     * Gets the cross-section of this solid when viewed from given direction.
     *
     * @param direction Direction to view this solid from
     * @return Cross-section
     */
    @Nonnegative
    double crossSection(@Nonnull Vector direction);

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

    /**
     * Gets the geometric centroid of this solid.
     *
     * @return Geometric centroid
     */
    @Nonnull
    Vector centroid();

    /**
     * Gets a list of corners of this solid.
     *
     * @return List of corners
     */
    @Nonnull
    List<Vector> corners();

    /**
     * Generates a list of vertices to render.
     *
     * @return Generated list of vertices
     */
    @Nonnull
    List<Vertex> vertices();
}
