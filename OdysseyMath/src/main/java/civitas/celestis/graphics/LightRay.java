package civitas.celestis.graphics;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>LightRay</h2>
 * <p>A ray of light</p>
 */
@Immutable
public class LightRay extends Ray {

    /**
     * Creates a new light ray.
     *
     * @param origin      Origin of ray
     * @param direction   Direction of ray
     * @param reflections Number of reflections remaining
     * @param intensity   Intensity of ray
     */
    public LightRay(@Nonnull Vector origin, @Nonnull Vector direction, long reflections, double intensity) {
        super(origin, direction, reflections);
        this.intensity = intensity;
    }

    /**
     * Creates a new light ray from a ray and intensity.
     *
     * @param ray       Ray to use
     * @param intensity Intensity of light
     */
    public LightRay(@Nonnull Ray ray, @Nonnegative double intensity) {
        super(ray);
        this.intensity = intensity;
    }

    @Nonnegative
    private double intensity;

    /**
     * Gets the current intensity of this light.
     *
     * @return Intensity
     */
    @Nonnegative
    public double intensity() {
        return intensity;
    }

    /**
     * Sets the intensity of this light.
     *
     * @param intensity Intensity
     */
    public void intensity(@Nonnegative double intensity) {
        this.intensity = intensity;
    }

    @Nonnull
    @Override
    public Ray reflect(@Nonnull Vector destination, @Nonnull Vector surfaceNormal) {
        // TODO Add reflectiveness coefficient to vertices
        return new LightRay(super.reflect(destination, surfaceNormal), intensity * 0.5);
    }
}
