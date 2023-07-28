package civitas.celestis.geometry.solid;


import civitas.celestis.geometry.vertex.ColoredVertex;
import civitas.celestis.geometry.vertex.Vertex;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

/**
 * <h2>Sphere</h2>
 * <p>A solid with no corners.</p>
 * @param centroid Geometric centroid of this sphere
 * @param rotation Rotation of this sphere
 * @param radius Radius of this sphere
 */
public record Sphere(
        @Nonnull Vector3 centroid,
        @Nonnull Quaternion rotation,
        @Nonnegative double radius
) implements Solid {
    @Nonnull
    @Override
    public List<Vertex> vertices() {
        // FIXME Put in an algorithm to do this
        final Vector3 a = centroid.add(new Vector3(radius, 0, 0).rotate(rotation));
        final Vector3 b = centroid.add(new Vector3(-radius, 0, 0).rotate(rotation));
        final Vector3 c = centroid.add(new Vector3(0, radius, 0).rotate(rotation));
        final Vector3 d = centroid.add(new Vector3(0, -radius, 0).rotate(rotation));
        final Vector3 e = centroid.add(new Vector3(0, 0, radius).rotate(rotation));
        final Vector3 f = centroid.add(new Vector3(0, 0, -radius).rotate(rotation));

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

    @Nonnull
    @Override
    public List<Vector3> corners() {
        return List.of(); // Spheres have no corners
    }

    @Override
    public boolean contains(@Nonnull Vector3 point) {
        return centroid.distance2(point) <= Math.pow(radius, 2);
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        if (other instanceof Sphere sphere) {
            return centroid.distance2(sphere.centroid) <= (Math.pow(radius, 2) + Math.pow(sphere.radius, 2));
        } else {
            for (Vector3 corner : other.corners()) {
                if (contains(corner)) return true;
            }

            return false;
        }
    }

    @Override
    public double volume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double surfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double dragCoefficient(@Nonnull Vector3 direction) {
        return 0.5; // Coefficient of drag of a perfect sphere
    }

    @Override
    public double crossSection(@Nonnull Vector3 direction) {
        return Math.PI * Math.pow(radius, 2);
    }
}
