package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * <h2>World</h2>
 * <p>Represents an in-game world.</p>
 */
public interface World {
    /**
     * Gets the unique identifier of this world.
     *
     * @return Unique identifier
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Gets the name of this world.
     *
     * @return Name
     */
    @Nonnull
    String getName();

    /**
     * Gets a list of objects in this world.
     *
     * @return List of objects
     */
    @Nonnull
    List<BaseObject> getObjects();

    /**
     * Gets an object by unique identifier.
     *
     * @param uniqueId Unique identifier of object
     * @return Object of unique identifier
     * @throws NullPointerException When an object of unique identifier cannot be found
     */
    @Nonnull
    BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Gets a list of overlapping object pairs.
     *
     * @return List of overlapping pairs
     */
    @Nonnull
    List<Pair<PhysicalObject>> getOverlaps();

    /**
     * Adds an object to this world.
     *
     * @param object Object to add
     */
    void addObject(@Nonnull BaseObject object);

    /**
     * Removes an object from this world.
     *
     * @param object Object to remove
     */
    void removeObject(@Nonnull BaseObject object);

    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);
}
