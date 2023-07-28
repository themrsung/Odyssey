package civitas.celestis.geometry.vertex;

import civitas.celestis.geometry.ray.LightRay;
import civitas.celestis.geometry.ray.Ray;
import civitas.celestis.geometry.solid.Solids;
import civitas.celestis.graphics.Colors;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>ColoredVertex</h2>
 * <p>A vertex with a single mutable color component.</p>
 */
public class ColoredVertex implements Vertex {
    /**
     * Creates a new colored vertex.
     * Reflectiveness is automatically derived from the given color.
     *
     * @param a     Point A of this vertex
     * @param b     Point B of this vertex
     * @param c     Point C of this vertex
     * @param color Color of this vertex
     */
    public ColoredVertex(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;

        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int avg = (red + green + blue) / 3;

        this.reflectiveness = 1 - ((double) avg / 255);
    }

    /**
     * Creates a new colored vertex.
     *
     * @param a              Point A of this vertex
     * @param b              Point B of this vertex
     * @param c              Point C of this vertex
     * @param color          Color of this vertex
     * @param reflectiveness Reflection coefficient of this vertex
     */
    public ColoredVertex(
            @Nonnull Vector3 a,
            @Nonnull Vector3 b,
            @Nonnull Vector3 c,
            @Nonnull Color color,
            @Nonnegative double reflectiveness
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
        this.reflectiveness = reflectiveness;
    }

    @Nonnull
    private final Vector3 a;
    @Nonnull
    private final Vector3 b;
    @Nonnull
    private final Vector3 c;
    @Nonnull
    private Color color;
    @Nonnegative
    private final double reflectiveness;

    @Nonnull
    @Override
    public Vector3 a() {
        return a;
    }

    @Nonnull
    @Override
    public Vector3 b() {
        return b;
    }

    @Nonnull
    @Override
    public Vector3 c() {
        return c;
    }

    @Nonnull
    @Override
    public Color color() {
        return color;
    }

    /**
     * Sets the color of this vertex.
     *
     * @param color Color of vertex
     */
    public void color(@Nonnull Color color) {
        this.color = color;
    }

    @Override
    public double reflectiveness() {
        return reflectiveness;
    }

    @Nonnull
    @Override
    public List<Vector3> points() {
        return List.of(a, b, c);
    }

    @Nonnull
    @Override
    public Vector3 centroid() {
        return a.add(b).add(c).divide(3);
    }

    @Nonnull
    @Override
    public Vector3 normal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    @Nullable
    @Override
    public Vector3 intersection(@Nonnull Ray ray) {
        if (!Solids.intersects(this, ray)) return null;

        final Vector3 n = normal();

        final double denominator = ray.direction().dot(n);
        if (denominator == 0) return null;

        final double length = (centroid().subtract(ray.origin()).dot(n)) / denominator;
        if (length < 0) return null;

        return ray.destination(length);
    }

    @Nonnull
    @Override
    public ColoredVertex inflate(double scale) {
        return new ColoredVertex(
                a.multiply(scale),
                b.multiply(scale),
                c.multiply(scale),
                color,
                reflectiveness
        );
    }

    @Nonnull
    @Override
    public ColoredVertex transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation) {
        return new ColoredVertex(
                a.subtract(origin).rotate(rotation),
                b.subtract(origin).rotate(rotation),
                c.subtract(origin).rotate(rotation),
                color,
                reflectiveness
        );
    }

    @Override
    public void onRayHit(@Nonnull Ray ray) {
        if (ray instanceof LightRay lr) {
            this.color = Colors.brighten(color, lr.intensity());
        }
    }

    @Override
    @Nonnull
    public Iterator<Vector3> iterator() {
        return points().iterator();
    }

    /**
     * Converts this vertex to a string.
     *
     * @return Stringified vertex
     */
    @Override
    @Nonnull
    public String toString() {
        return "ColoredVertex{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", color=" + color +
                '}';
    }
}
