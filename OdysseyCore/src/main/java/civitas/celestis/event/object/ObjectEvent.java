package civitas.celestis.event.object;

import civitas.celestis.event.Event;
import civitas.celestis.object.OdysseyObject;

import javax.annotation.Nonnull;

/**
 * <h2>ObjectEvent</h2>
 * <p>A superclass for events regarding one object.</p>
 */
public abstract class ObjectEvent implements Event {
    /**
     * Creates a new object event.
     *
     * @param object Object involved in this event
     */
    public ObjectEvent(@Nonnull OdysseyObject object) {
        this.object = object;
    }

    @Nonnull
    private final OdysseyObject object;

    /**
     * Gets the object involved in this event.
     *
     * @return Object of event
     */
    @Nonnull
    public OdysseyObject getObject() {
        return object;
    }
}
