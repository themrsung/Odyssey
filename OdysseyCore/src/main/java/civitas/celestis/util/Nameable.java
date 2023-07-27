package civitas.celestis.util;

import javax.annotation.Nonnull;

/**
 * <h2>Nameable</h2>
 * <p>Nameable objects have a unique name.</p>
 */
public interface Nameable {
    /**
     * Gets the name of this object.
     *
     * @return Name
     */
    @Nonnull
    String getName();
}
