package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * <h2>World</h2>
 * <p>Represents an in-game level.</p>
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
     * @return Name of world
     */
    @Nonnull
    String getName();

    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    /**
     * Gets a list of all objects in this world.
     *
     * @return List of objects
     */
    @Nonnull
    List<BaseObject> getObjects();

    /**
     * Gets a filtered stream of objects in this world.
     *
     * @param objectClass Type of object to get
     * @param <O>         Type of object to get
     * @return Stream of filtered and cast objects
     */
    @Nonnull
    <O extends BaseObject> Stream<O> getObjects(@Nonnull Class<O> objectClass);

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
     * Gets an object by unique identifier.
     *
     * @param uniqueId Unique identifier of object
     * @param type     Type of object to get
     * @param <O>      Class of object to get
     * @return Object of type and unique identifier
     * @throws NullPointerException When an object of matching type and/or unique identifier cannot be found
     */
    @Nonnull
    <O extends BaseObject> O getObject(@Nonnull UUID uniqueId, @Nonnull Class<O> type) throws NullPointerException;

    /**
     * Adds an object to this world.
     *
     * @param object Object to add
     * @param <O>    Type of object to add
     */
    <O extends BaseObject> void addObject(@Nonnull O object);

    /**
     * Removes an object from this world.
     *
     * @param object Object to remove
     * @param <O>    Type of object to remove
     */
    <O extends BaseObject> void removeObject(@Nonnull O object);
}
