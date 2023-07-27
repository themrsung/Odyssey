package civitas.celestis;

import civitas.celestis.geometry.ColoredVertex;
import civitas.celestis.geometry.Vertex;
import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;
import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Viewport extends JPanel {
    public Viewport(World world, Vector location, Quaternion rotation) {
        super(true);
        this.world = world;
        this.location = location;
        this.rotation = rotation;
    }

    private final World world;
    private Vector location;
    private Quaternion rotation;

    public Vector getOrigin() {
        return location;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setOrigin(Vector origin) {
        this.location = origin;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    @Override
    public void paint(Graphics g) {
        if (painting) return;

        // Thread-safe
        painting = true;

        // Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        vertices.clear();

        world.getObjects().stream().filter(PhysicalObject.class::isInstance).map(PhysicalObject.class::cast).forEach(o -> {
            final Solid s = o.getSolid();
            s.vertices().forEach(v -> vertices.add(v.transform(location, rotation).inflate(10)));
        });

        g.translate(getWidth() / 2, getHeight() / 2);

        vertices.sort((v1, v2) -> Double.compare(v2.centroid().distance2(location), v1.centroid().distance2(location)));

        vertices.forEach(v -> {
            for (Vector p : v) {
                if (p.z() < 0) return;
            }

            final Polygon polygon = new Polygon();

            v.forEach(p -> {
                final Pair<Integer> coords = map(p); // Inflate coordinates
                polygon.addPoint(coords.first(), coords.second());
            });

            final Color color;
            if (v instanceof ColoredVertex cv) {
                color = cv.color();
            } else {
                color = Color.CYAN;
            }
            g.setColor(color);
            g.fillPolygon(polygon);
        });

        painting = false;
    }

    private Pair<Integer> map(@Nonnull Vector coordinate) {
        final double focalLength = 500;
        return new Pair<>(
                (int) ((focalLength / (focalLength + coordinate.z())) * coordinate.x()),
                (int) ((focalLength / (focalLength + coordinate.z())) * -coordinate.y())
        );
    }

    private boolean painting = false;
    private final List<Vertex> vertices = new ArrayList<>();
}
