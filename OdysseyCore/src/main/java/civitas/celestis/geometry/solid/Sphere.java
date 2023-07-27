package civitas.celestis.geometry.solid;

import civitas.celestis.geometry.ColoredVertex;
import civitas.celestis.geometry.Vertex;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.awt.*;
import java.util.List;

/**
 * <h2>Sphere</h2>
 * <p>A solid with no corners.</p>
 */
@Immutable
public class Sphere implements Solid {
    /**
     * Creates a new sphere.
     *
     * @param centroid Centroid of sphere
     * @param rotation Rotation of this sphere
     * @param radius   Radius of sphere
     */
    public Sphere(@Nonnull Vector centroid, @Nonnull Quaternion rotation, @Nonnegative double radius) {
        this.centroid = centroid;
        this.rotation = rotation;
        this.radius = radius;
    }

    @Nonnull
    private final Vector centroid;
    @Nonnull
    private final Quaternion rotation;
    @Nonnegative
    private final double radius;

    @Nonnull
    @Override
    public Vector centroid() {
        return centroid;
    }

    /**
     * Gets the rotation of this sphere.
     *
     * @return Rotation
     */
    @Nonnull
    public Quaternion rotation() {
        return rotation;
    }

    /**
     * Gets the radius of this sphere.
     *
     * @return Radius
     */
    @Nonnegative
    public double radius() {
        return radius;
    }

    @Override
    public boolean contains(@Nonnull Vector point) {
        return centroid.distance2(point) <= Math.pow(radius, 2);
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        if (other instanceof Sphere sphere) {
            return centroid.distance2(sphere.centroid) <= (Math.pow(radius, 2) + Math.pow(sphere.radius, 2));
        } else {
            for (Vector corner : other.corners()) {
                if (contains(corner)) return true;
            }

            return false;
        }
    }

    @Override
    public double dragCoefficient(@Nonnull Vector direction) {
        return 0.5;
    }

    @Override
    public double crossSection(@Nonnull Vector direction) {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double volume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double surfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Nonnull
    @Override
    public List<Vector> corners() {
        return List.of();
    }

    @Nonnull
    @Override
    public List<Vertex> vertices() {
        final Vector a = centroid.add(new Vector(radius, 0, 0).rotate(rotation));
        final Vector b = centroid.add(new Vector(-radius, 0, 0).rotate(rotation));
        final Vector c = centroid.add(new Vector(0, radius, 0).rotate(rotation));
        final Vector d = centroid.add(new Vector(0, -radius, 0).rotate(rotation));
        final Vector e = centroid.add(new Vector(0, 0, radius).rotate(rotation));
        final Vector f = centroid.add(new Vector(0, 0, -radius).rotate(rotation));

        return List.of(
                new ColoredVertex(a, f, d, Color.RED),
                new ColoredVertex(a, c, f, Color.BLUE),
                new ColoredVertex(b, f, c, Color.GREEN),
                new ColoredVertex(d, f, b, Color.CYAN),
                new ColoredVertex(a, e, c, Color.PINK),
                new ColoredVertex(a, d, e, Color.GRAY),
                new ColoredVertex(c, e, b, Color.DARK_GRAY),
                new ColoredVertex(b, e, d, Color.YELLOW)
        );
    }
}
