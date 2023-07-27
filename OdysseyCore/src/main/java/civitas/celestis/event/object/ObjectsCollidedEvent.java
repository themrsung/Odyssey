package civitas.celestis.event.object;

import civitas.celestis.event.Cancellable;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;

import javax.annotation.Nonnull;

/**
 * <h2>ObjectsCollidedEvent</h2>
 * <p>Called when two physical objects overlap for the first time.</p>
 */
public final class ObjectsCollidedEvent implements Cancellable {
    /**
     * Creates a new objects collided event.
     *
     * @param objects Pair of objects which have collided
     */
    public ObjectsCollidedEvent(@Nonnull Pair<PhysicalObject> objects) {
        this.objects = objects;
    }

    @Nonnull
    private final Pair<PhysicalObject> objects;
    private boolean cancelled;

    /**
     * Gets the pair of objects which collided.
     *
     * @return Pair of collided objects
     */
    @Nonnull
    public Pair<PhysicalObject> getObjects() {
        return objects;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
