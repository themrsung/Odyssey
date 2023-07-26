package civitas.celestis.event.object;

import civitas.celestis.object.OdysseyObject;

import javax.annotation.Nonnull;

/**
 * <h2>ObjectEvent</h2>
 * <p>An event involving one object.</p>
 */
public abstract class ObjectEvent {
    public ObjectEvent(@Nonnull OdysseyObject object) {
        this.object = object;
    }

    @Nonnull
    private final OdysseyObject object;

    /**
     * Gets the object involved in this event.
     * @return Object
     */
    @Nonnull
    public OdysseyObject getObject() {
        return object;
    }
}
