package civitas.celestis.gui.component;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;

import javax.annotation.Nonnull;

/**
 * <h2>ViewportContext</h2>
 * <p>Holds the contextual information required to render a viewport.</p>
 *
 * @param origin   Origin of viewport
 * @param rotation Rotation of viewport
 */
public record ViewportContext(
        @Nonnull Vector3 origin,
        @Nonnull Quaternion rotation
) {
    /**
     * Gets an empty viewport context object.
     *
     * @return Empty viewport context
     */
    @Nonnull
    public static ViewportContext getEmptyContext() {
        return new ViewportContext(Vector3.ZERO, Quaternion.IDENTITY);
    }
}
