package civitas.celestis.world;

import civitas.celestis.object.OdysseyObject;
import civitas.celestis.util.Identifiable;
import civitas.celestis.util.Nameable;

import javax.annotation.Nonnull;
import java.util.List;

public interface World extends Identifiable, Nameable {
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
