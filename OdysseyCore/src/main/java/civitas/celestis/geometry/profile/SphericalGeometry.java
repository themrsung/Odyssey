package civitas.celestis.geometry.profile;

import civitas.celestis.geometry.solid.Sphere;
import civitas.celestis.object.BaseObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>SphericalGeometry</h2>
 * <p>A spherical geometric profile.</p>
 */
@Immutable
public class SphericalGeometry implements Geometry {
    /**
     * Creates a new spherical geometric profile.
     *
     * @param radius Radius of this sphere
     */
    public SphericalGeometry(@Nonnegative double radius) {
        this.radius = radius;
    }

    @Nonnegative
    private final double radius;

    /**
     * Gets the radius of this sphere.
     *
     * @return Radius
     */
    @Nonnegative
    public double radius() {
        return radius;
    }

    @Override
    public double volume() {
        return 4.0 / 3.0 * Math.PI * radius;
    }

    @Override
    public double surfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Nonnull
    @Override
    public Sphere generate(@Nonnull BaseObject object) {
        return new Sphere(object.getLocation(), object.getRotation(), radius);
    }
}
