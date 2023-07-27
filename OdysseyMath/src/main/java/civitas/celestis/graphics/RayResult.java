package civitas.celestis.graphics;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>RayResult</h2>
 * <p>The result of a ray shoot.</p>
 */
public class RayResult {
    public RayResult() {
        this.reflections = new ArrayList<>();
    }

    @Nonnull
    private final List<Reflection> reflections;

    /**
     * Gets the list of reflections of this ray result.
     *
     * @return List of reflections
     */
    @Nonnull
    public List<Reflection> reflections() {
        return List.copyOf(reflections);
    }

    /**
     * Adds a reflection to this result.
     *
     * @param reflection Reflection to add
     */
    public void reflection(@Nonnull Reflection reflection) {
        reflections.add(reflection);
    }

    /**
     * <h2>Reflection</h2>
     * <p>The reflection from a ray-vertex collision.</p>
     *
     * @param surface Surface of collision
     * @param ray     Derived ray
     */
    public record Reflection(
            @Nonnull Vertex surface,
            @Nonnull Ray ray
    ) {}
}
