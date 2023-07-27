package civitas.celestis.graphics;

import civitas.celestis.geometry.LightRay;
import civitas.celestis.geometry.Vertex;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
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
        this.vertices = new ArrayList<>();
    }

    /**
     * Creates a scene with precomputed vertices.
     *
     * @param vertices List of precomputed vertices
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
     * Gets a list of vertices.
     *
     * @return List of vertices
     */
    @Nonnull
    public List<Vertex> getVertices() {
        return List.copyOf(vertices);
    }

    /**
     * Gets a transformed list of vertices.
     *
     * @param origin   Origin of transformation
     * @param rotation Rotation of transformation
     * @return Stream of transformed vertices
     */
    @Nonnull
    public Stream<Vertex> getTransformedVertices(@Nonnull Vector origin, @Nonnull Quaternion rotation) {
        return List.copyOf(vertices).stream().map(v -> v.transform(origin, rotation));
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
     * Removes a vertex from this scene.
     *
     * @param vertex Vertex to remove
     */
    public void removeVertex(@Nonnull Vertex vertex) {
        vertices.remove(vertex);
    }

    /**
     * Clears all data in this scene.
     */
    public void clear() {
        vertices.clear();
    }

    //
    // Lighting
    //

    /**
     * Shoots a light ray to this scene.
     * <p>
     * If the limit is reached before the ray terminates,
     * the ray will be forcefully terminated.
     * </p>
     * <p>
     * If the ray cannot find a vertex to hit,
     * it will be preemptively terminated.
     * </p>
     *
     * @param ray   Ray to shoot
     * @param limit Maximum allowed reflections
     */
    public void shootLightRay(@Nonnull LightRay ray, @Nonnegative int limit) {
        shootLightRay(ray, limit, null);
    }

    /**
     * Internal method for recursive calling.
     *
     * @param ray    Ray to shoot
     * @param limit  Remaining reflections
     * @param origin Origin vertex
     */
    protected void shootLightRay(@Nonnull LightRay ray, @Nonnegative int limit, @Nullable Vertex origin) {
        if (limit == 0) return;

        // Copies list to prevent concurrent modification
        final List<Vertex> vertices = new ArrayList<>(this.vertices);

        // Use squared distance to avoid sqrt()
        vertices.sort(Comparator.comparing(v -> v.centroid().distance2(ray.origin())));

        // Prevent self-reflection
        if (origin != null) vertices.remove(origin);

        for (Vertex v : vertices) {
            final LightRay r = v.reflection(ray);
            if (r != null) {
                // Call onLightRayHit()
                v.onLightRayHit(r);

                // Shoot next ray
                shootLightRay(r, limit - 1, v);
                return;
            }
        }
    }

}
