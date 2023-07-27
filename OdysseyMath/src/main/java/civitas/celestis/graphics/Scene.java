package civitas.celestis.graphics;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <h2>Scene</h2>
 * <p>Represents a 3D scene.</p>
 */
public class Scene {
    /**
     * Creates an empty scene.
     */
    public Scene() {
        this(new ArrayList<>());
    }

    /**
     * Creates a scene with predetermined vertices.
     *
     * @param vertices List of vertices
     */
    public Scene(@Nonnull List<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Nonnull
    protected final List<Vertex> vertices;

    /**
     * Gets a list of vertices in this screen.
     *
     * @return List of vertices
     */
    @Nonnull
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * Adds a vertex to this scene.
     *
     * @param vertex Vertex to add
     */
    public void addVertex(@Nonnull Vertex vertex) {
        vertices.add(vertex);
    }

    /**
     * Removes a vertex from this screen.
     *
     * @param vertex Vertex to remove
     */
    public void removeVertex(@Nonnull Vertex vertex) {
        vertices.remove(vertex);
    }

    /**
     * Shoots a ray to this scene.
     *
     * @param ray Ray to shoot
     * @return Result of operation
     */
    @Nonnull
    RayResult shootRay(@Nonnull Ray ray) {
        final RayResult result = new RayResult();

        final List<Vertex> vertices = new ArrayList<>(this.vertices);
        vertices.sort(Comparator.comparing(v -> v.centroid().distance(ray.origin())));

        Ray lastRay = ray;

        while (lastRay.reflections() > 0) {
            Vector intersection = null;
            Vertex surface = null;

            for (Vertex vertex : vertices) {
                final Vector i = vertex.intersection(lastRay);
                if (i != null) {
                    intersection = i;
                    surface = vertex;

                    if (ray instanceof LightRay light) {
                        final Color original = vertex.color();
                        final double intensity = light.intensity();

                        final Color brighter = new Color(
                                (int) (original.getRed() + intensity),
                                (int) (original.getGreen() + intensity),
                                (int) (original.getBlue() + intensity),
                                original.getAlpha()
                        );

                        vertex.color(brighter);
                    }
                }
            }

            // Operation complete
            if (intersection == null) return result;

            // Move on to next ray
            lastRay = ray.reflect(intersection, surface.surfaceNormal());
        }

        return result;
    }
}
