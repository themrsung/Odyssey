package civitas.celestis.number;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Vectors</h2>
 * <p>Contains vector utility functions.</p>
 */
public final class Vectors {
    /**
     * Translates a 3D vector to a 2D coordinate system.
     * <p>
     * This assumes that Z represents depth,
     * and inverts Y to be compatible with the AWT coordinate system.
     * </p>
     *
     * @param v3          3D vector
     * @param focalLength Focal length to use
     * @return Translated 2D vector
     */
    @Nonnull
    public static Vector2 translate(@Nonnull Vector3 v3, @Nonnegative double focalLength) {
        final double z = ((focalLength / (focalLength + v3.z())));
        return new Vector2(
                z * v3.x(),
                z * -v3.y()
        );
    }

    /**
     * Translates a 2D vector to a 3D coordinate system.
     * <p>
     * This passes the X and Y coordinates as is, and uses the provided Z parameter as its depth.
     * Note that smaller Z values will appear to be in front of larger Z values.
     * </p>
     * <p>
     * This is NOT an inverse operation of {@link Vectors#translate(Vector3, double)}.
     * This method is intended to be used as a coordinate translator for
     * 2D projects in order to use the 3D rendering engine.
     * </p>
     *
     * @param v2 Vector to use
     * @param z  Z value to use
     * @return Translated 3D vector
     */
    @Nonnull
    public static Vector3 translate(@Nonnull Vector2 v2, double z) {
        return new Vector3(v2.x(), v2.y(), z);
    }

    /**
     * Given an input vector and a surface normal, this returns the reflection vector.
     *
     * @param in     Input vector
     * @param normal Surface normal
     * @return Reflection vector
     */
    @Nonnull
    public static Vector3 reflection(@Nonnull Vector3 in, @Nonnull Vector3 normal) {
        return in.subtract(normal.multiply(2 * in.dot(normal)));
    }
}
