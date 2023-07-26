package civitas.celestis.event.object;

import civitas.celestis.event.Event;
import civitas.celestis.object.OdysseyObject;
import civitas.celestis.util.Pair;

import javax.annotation.Nonnull;

/**
 * <h2>ObjectPairEvent</h2>
 * <p>A superclass for events involving a pair of objects.</p>
 */
public abstract class ObjectPairEvent implements Event {
    /**
     * Creates a new object pair event.
     *
     * @param objects Pair of objects
     */
    public ObjectPairEvent(@Nonnull Pair<OdysseyObject> objects) {
        this.objects = objects;
    }

    @Nonnull
    private final Pair<OdysseyObject> objects;

    /**
     * Gets the pair of objects involved in this event.
     *
     * @return Pair of objects
     */
    @Nonnull
    public Pair<OdysseyObject> getObjects() {
        return objects;
    }
}
