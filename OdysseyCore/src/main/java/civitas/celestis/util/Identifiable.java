package civitas.celestis.util;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>Identifiable</h2>
 * <p>Identifiable objects have a unique identifier.</p>
 */
public interface Identifiable {
    /**
     * Gets the unique identifier of this object.
     *
     * @return Unique identifier of object
     */
    @Nonnull
    UUID getUniqueId();
}
