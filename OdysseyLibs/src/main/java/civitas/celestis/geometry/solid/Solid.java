package civitas.celestis.geometry.solid;

import civitas.celestis.geometry.vertex.Vertex;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>Represents a three-dimensional shape.</p>
 */
public interface Solid {
    //
    // Graphics
    //

    /**
     * Gets a list of vertices which can be rendered to a scene.
     * @return List of vertices
     */
    @Nonnull
    List<Vertex> vertices();


}
