package civitas.celestis.geometry;

import civitas.celestis.number.Vector;
import civitas.celestis.number.Vectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

/**
 * <h2>LightRay</h2>
 * <p>A ray which represents light.</p>
 */
public class LightRay implements Ray {
    /**
     * Creates a new light ray.
     * Directional vector is automatically normalized.
     *
     * @param origin    Origin of ray
     * @param direction Direction of ray
     * @param color     Color of ray
     * @param intensity Intensity of ray
     */
    public LightRay(@Nonnull Vector origin, @Nonnull Vector direction, @Nonnull Color color, @Nonnegative double intensity) {
        this.origin = origin;
        this.direction = direction.normalize();
        this.color = color;
        this.intensity = intensity;
    }

    /**
     * Creates a new while-light ray.
     *
     * @param origin    Origin of ray
     * @param direction Direction of ray
     * @param intensity Intensity of ray
     */
    public LightRay(@Nonnull Vector origin, @Nonnull Vector direction, @Nonnegative double intensity) {
        this(origin, direction, Color.WHITE, intensity);
    }

    @Nonnull
    private final Vector origin;
    @Nonnull
    private final Vector direction;
    @Nonnull
    private Color color;
    @Nonnegative
    private double intensity;

    @Nonnull
    @Override
    public Vector origin() {
        return origin;
    }

    @Nonnull
    @Override
    public Vector direction() {
        return direction;
    }

    @Nonnull
    @Override
    public Vector destination(double length) {
        return origin.add(direction.multiply(length));
    }

    @Nullable
    @Override
    public LightRay reflection(@Nonnull Vertex surface) {
        final Vector intersection = surface.intersection(this);
        if (intersection == null) return null;

        final Vector reflection = Vectors.reflection(direction, surface.normal());
        return new LightRay(intersection, reflection, color, intensity * surface.reflectiveness());
    }

    /**
     * Gets the color of this light ray.
     *
     * @return Color
     */
    @Nonnull
    public Color color() {
        return color;
    }

    /**
     * Changes the color of this ray.
     *
     * @param color Color of ray
     */
    public void color(@Nonnull Color color) {
        this.color = color;
    }

    /**
     * Gets the intensity of this light ray.
     *
     * @return Intensity
     */
    @Nonnegative
    public double intensity() {
        return intensity;
    }

    /**
     * Changes the intensity of this ray.
     *
     * @param intensity Intensity of ray
     */
    public void intensity(@Nonnegative double intensity) {
        this.intensity = intensity;
    }

    /**
     * Converts this ray to a string.
     *
     * @return Stringified ray
     */
    @Override
    @Nonnull
    public String toString() {
        return "LightRay{" +
                "origin=" + origin +
                ", direction=" + direction +
                ", color=" + color +
                ", intensity=" + intensity +
                '}';
    }
}
