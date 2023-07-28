package civitas.celestis.geometry.profile;

import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.geometry.solid.Sphere;
import civitas.celestis.object.TangibleObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>SphericalGeometry</h2>
 * <p>A spherical geometric profile.</p>
 *
 * @param radius Radius of this sphere
 */
public record SphericalGeometry(
        @Nonnegative double radius
) implements Geometry {
    @Override
    public double volume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    @Nonnull
    @Override
    public Solid build(@Nonnull TangibleObject object) {
        return new Sphere(object.getLocation(), object.getRotation(), radius);
    }
}
