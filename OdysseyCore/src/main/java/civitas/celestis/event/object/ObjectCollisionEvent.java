package civitas.celestis.event.object;

import civitas.celestis.event.Cancellable;
import civitas.celestis.object.OdysseyObject;
import civitas.celestis.util.Pair;

import javax.annotation.Nonnull;

/**
 * <h2>ObjectCollisionEvent</h2>
 * <p>Denotes the collision of two objects.</p>
 */
public final class ObjectCollisionEvent extends ObjectPairEvent implements Cancellable {
    /**
     * Creates a new object collision event.
     *
     * @param objects Pair of objects which collided
     */
    public ObjectCollisionEvent(@Nonnull Pair<OdysseyObject> objects) {
        super(objects);
        this.cancelled = false;
    }

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
