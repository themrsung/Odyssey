package civitas.celestis.graphics;

import civitas.celestis.object.BaseObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>ViewportConfiguration</h2>
 * <p>Configuration of a viewport.</p>
 */
public record ViewportConfiguration(
        @Nonnegative double inflation,
        @Nonnegative double focalLength,
        @Nonnull List<BaseObject> hiddenObjects
) {
    /**
     * Gets a configuration object with default settings.
     *
     * @return Default configuration
     */
    @Nonnull
    public static ViewportConfiguration getDefaultConfiguration() {
        return new ViewportConfiguration(10, 300, new ArrayList<>());
    }

    /**
     * Gets a list of hidden objects.
     *
     * @return List of hidden objects
     */
    @Override
    @Nonnull
    public List<BaseObject> hiddenObjects() {
        return List.copyOf(hiddenObjects);
    }
}
