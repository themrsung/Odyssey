package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import java.awt.*;

/**
 * <h2>ColoredVertex</h2>
 * <p>A vertex with a mutable color component.</p>
 */
public final class ColoredVertex extends Vertex {
    /**
     * Creates a new colored vertex.
     *
     * @param a     Point A of this vertex
     * @param b     Point B of this vertex
     * @param c     Point C of this vertex
     * @param color Color of this vertex
     */
    public ColoredVertex(@Nonnull Vector a, @Nonnull Vector b, @Nonnull Vector c, @Nonnull Color color) {
        super(a, b, c);
        this.color = color;
    }

    /**
     * Creates a new vertex.
     *
     * @param other Vertex to use
     * @param color Color to use
     */
    public ColoredVertex(@Nonnull Vertex other, @Nonnull Color color) {
        super(other);
        this.color = color;
    }

    @Nonnull
    private Color color;

    /**
     * Gets the color of this vertex.
     *
     * @return Color
     */
    @Nonnull
    public Color color() {
        return color;
    }

    /**
     * Sets the color of this vertex.
     *
     * @param color Color
     */
    public void color(@Nonnull Color color) {
        this.color = color;
    }
}
