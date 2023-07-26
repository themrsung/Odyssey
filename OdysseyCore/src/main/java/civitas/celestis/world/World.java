package civitas.celestis.world;

import civitas.celestis.io.Deserializer;
import civitas.celestis.io.Serializer;
import civitas.celestis.object.OdysseyObject;
import civitas.celestis.util.Identifiable;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * <h2>World</h2>
 * <p>Represents an in-game world.</p>
 */
public interface World extends Identifiable, Serializable {
    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    //
    // Identification
    //

    /**
     * Gets the name of this world.
     *
     * @return Name
     */
    @Nonnull
    String getName();

    //
    // Objects
    //

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

    //
    // IO
    //

    /**
     * Gets a deserializer instance of {@code this}.
     *
     * @return Deserializer instance
     */
    @Nonnull
    Deserializer<? extends World> deserializer();

    /**
     * Gets a serializer instance of {@code this}.
     *
     * @return Serializer instance
     */
    @Nonnull
    Serializer<? extends World> serializer();
}
