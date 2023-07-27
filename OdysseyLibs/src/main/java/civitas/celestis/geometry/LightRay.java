package civitas.celestis.geometry;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>LightRay</h2>
 * <p>A ray of light.</p>
 */
@Immutable
public class LightRay extends Ray {
    /**
     * Creates a new light ray.
     *
     * @param origin    Origin of this ray
     * @param direction Direction of this ray
     * @param intensity Intensity of this ray
     */
    public LightRay(@Nonnull Vector origin, @Nonnull Vector direction, @Nonnegative double intensity) {
        super(origin, direction);
        this.intensity = intensity;
    }

    /**
     * Creates a light ray from an existing ray.
     *
     * @param other     Ray to use
     * @param intensity Intensity of this ray
     */
    public LightRay(@Nonnull Ray other, @Nonnegative double intensity) {
        super(other);
        this.intensity = intensity;
    }

    @Nonnegative
    private final double intensity;

    /**
     * Gets the intensity of this light ray.
     *
     * @return Intensity
     */
    @Nonnegative
    public double intensity() {
        return intensity;
    }
}
