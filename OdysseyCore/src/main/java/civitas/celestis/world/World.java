package civitas.celestis.world;

import civitas.celestis.object.OdysseyObject;
import civitas.celestis.util.Identifiable;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * <h2>World</h2>
 * <p>Represents an in-game world.</p>
 */
public interface World extends Identifiable {
    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    /**
     * Gets a list of objects in this world.
     *
     * @return List of objects
     */
    @Nonnull
    List<OdysseyObject> getObjects();

    /**
     * Gets an object by unique identifier.
     *
     * @param uniqueId Unique identifier of object
     * @return Object of given unique identifier
     * @throws NullPointerException When an object of matching unique identifier cannot be found
     */
    @Nonnull
    OdysseyObject getObject(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Adds an object to this world.
     *
     * @param object Object to add
     * @throws IllegalArgumentException When the object is already present in this world
     */
    void addObject(@Nonnull OdysseyObject object) throws IllegalArgumentException;

    /**
     * Removes an object from this world.
     *
     * @param object Object to remove
     */
    void removeObject(@Nonnull OdysseyObject object);
}
