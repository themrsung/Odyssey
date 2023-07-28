package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>Solids</h2>
 * <p>Contains solid utility functions.</p>
 */
public final class Solids {
    /**
     * Gets the signed volume of a tetrahedron.
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     * @param d Point D
     * @return Signed volume of tetrahedron
     */
    public static double signedVolume(@Nonnull Vector a, @Nonnull Vector b, @Nonnull Vector c, @Nonnull Vector d) {
        return (1.0 / 6.0) * b.subtract(a).cross(c.subtract(a)).dot(d.subtract(a));
    }

    /**
     * Checks if given vertex and ray intersects.
     *
     * @param vertex Vertex to check
     * @param ray    Ray to check
     * @return {@code true} if the two objects intersect
     */
    public static boolean intersects(@Nonnull Vertex vertex, @Nonnull Ray ray) {
        final Vector p1 = vertex.a();
        final Vector p2 = vertex.b();
        final Vector p3 = vertex.c();

        final Vector q1 = ray.origin();
        final Vector q2 = ray.destination(ray.origin().distance2(vertex.centroid()));

        // Different sign
        final double a = signedVolume(q1, p1, p2, p3);
        final double b = signedVolume(q2, p1, p2, p3);

        // Same sign
        final double c = signedVolume(q1, q2, p1, p2);
        final double d = signedVolume(q1, q2, p2, p3);
        final double e = signedVolume(q1, q2, p3, p1);

        return a * b < 0 && c * d * e >= 0;
    }
}
