package civitas.celestis.geometry.solid;

import civitas.celestis.geometry.ray.Ray;
import civitas.celestis.geometry.vertex.Vertex;
import civitas.celestis.number.Vector3;

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
    public static double signedVolume(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Vector3 d) {
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
        final Vector3 p1 = vertex.a();
        final Vector3 p2 = vertex.b();
        final Vector3 p3 = vertex.c();

        final Vector3 q1 = ray.origin();
        final Vector3 q2 = ray.destination(ray.origin().distance2(vertex.centroid()));

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
