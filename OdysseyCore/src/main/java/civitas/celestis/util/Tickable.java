package civitas.celestis.util;

import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Tickable</h2>
 * <p>An object which can be ticked regularly.</p>
 */
public interface Tickable {
    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);
}
