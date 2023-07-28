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
