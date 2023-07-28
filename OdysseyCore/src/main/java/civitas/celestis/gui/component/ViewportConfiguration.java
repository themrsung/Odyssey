package civitas.celestis.gui.component;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>ViewportConfiguration</h2>
 * <p>Holds the configuration of a viewport.</p>
 *
 * @param renderDistance Maximum render distance
 * @param focalLength    Focal length of viewport
 */
public record ViewportConfiguration(
        @Nonnegative double renderDistance,
        @Nonnegative double inflation,
        @Nonnegative double focalLength
) {
    /**
     * Gets the default configuration.
     *
     * @return Default viewport configuration
     */
    @Nonnull
    public static ViewportConfiguration getDefaultConfiguration() {
        return new ViewportConfiguration(10000, 10, 300);
    }
}
