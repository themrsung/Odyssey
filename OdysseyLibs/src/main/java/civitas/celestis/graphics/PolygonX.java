package civitas.celestis.graphics;

import civitas.celestis.number.Vector2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

/**
 * <h2>PolygonX</h2>
 * <p>A polygon which supports Vector2 input.</p>
 */
public class PolygonX extends Polygon {
    /**
     * Default constructor.
     */
    public PolygonX() {
    }

    /**
     * Creates a polygon with predetermined points.
     *
     * @param x X coordinates
     * @param y Y coordinates
     * @param n Number of points
     */
    public PolygonX(@Nonnull int[] x, @Nonnull int[] y, @Nonnegative int n) {
        super(x, y, n);
    }

    /**
     * Adds a point denoted in Vector2.
     *
     * @param v2 Coordinates of point
     */
    public void addPoint(@Nonnull Vector2 v2) {
        final int x = (int) Math.round(v2.x());
        final int y = (int) Math.round(v2.y());

        super.addPoint(x, y);
    }

    /**
     * Adds multiple points to this polygon.
     *
     * @param points List of points to add
     */
    public void addPoints(@Nonnull List<Vector2> points) {
        points.forEach(this::addPoint);
    }
}
