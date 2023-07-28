package civitas.celestis.graphics;

import civitas.celestis.geometry.ray.Ray;
import civitas.celestis.geometry.vertex.Vertex;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * <h2>Scene</h2>
 * <p>
 * An object which contains all information required to render a world.
 * All computations are done in this object which is placed in a JPanel
 * to ensure that graphics workload is distributed to GPU cores.
 * </p>
 * <p>
 * Every method is designed to be thread-safe.
 * </p>
 */
public class Scene {
    //
    // Constructors
    //

    /**
     * Creates an empty scene.
     */
    public Scene() {
        this(new ArrayList<>());
    }

    /**
     * Creates a scene from a list of predefined vertices.
     *
     * @param vertices List of vertices
     */
    public Scene(@Nonnull List<Vertex> vertices) {
        this.vertices = vertices;
    }

    //
    // Vertices
    //
    @Nonnull
    protected final List<Vertex> vertices;

    /**
     * Gets a list of vertices in this scene.
     *
     * @return List of vertices
     */
    @Nonnull
    public List<Vertex> getVertices() {
        return List.copyOf(vertices);
    }

    /**
     * Gets a stream of transformed vertices.
     *
     * @param origin    Origin of transformation
     * @param rotation  Rotation of transformation
     * @param inflation Inflation to apply
     * @return Transformed stream of vertices
     */
    @Nonnull
    public Stream<Vertex> getVertices(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double inflation) {
        return List.copyOf(vertices).stream().map(v -> v.transform(origin, rotation).inflate(inflation));
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
     * Adds a collection of vertices to this scene.
     *
     * @param vertices Collection of vertices
     */
    public void addVertices(@Nonnull Collection<Vertex> vertices) {
        this.vertices.addAll(vertices);
    }

    /**
     * Removes a vertex from this scene.
     *
     * @param vertex Vertex to remove
     */
    public void removeVertex(@Nonnull Vertex vertex) {
        vertices.remove(vertex);
    }

    /**
     * Removes a collection of vertices from this scene.
     *
     * @param vertices Collection of vertices
     */
    public void removeVertices(@Nonnull Collection<Vertex> vertices) {
        this.vertices.removeAll(vertices);
    }

    /**
     * Clears all rendered data in this scene.
     */
    public void clear() {
        vertices.clear();
    }

    //
    // Raytracing
    //

    /**
     * Shoots a ray into this scene.
     *
     * @param ray   Ray to shoot
     * @param limit Maximum allowed number of reflections
     */
    public void shootRay(@Nonnull Ray ray, @Nonnegative int limit) {
        shootRay(ray, limit, null);
    }

    /**
     * Internal method for recursive calling.
     *
     * @param ray    Ray to shoot
     * @param limit  Remaining number of reflections
     * @param origin Vertex this ray originated from
     */
    protected void shootRay(@Nonnull Ray ray, @Nonnegative int limit, @Nullable Vertex origin) {
        if (limit == 0) return; // Terminate

        final List<Vertex> vertices = getVertices();
        if (origin != null) vertices.remove(origin); // Exclude origin

        // Sort by distance to minimize unnecessary iterations
        vertices.sort(Comparator.comparing(v -> v.centroid().distance2(ray.origin())));

        for (Vertex v : vertices) {
            final Ray reflection = ray.reflection(v);
            if (reflection == null) continue;

            // Notify vertex
            v.onRayHit(ray);

            // Shoot reflection ray
            shootRay(reflection, limit - 1, v);
            return; // Break loop
        }
    }
}
