package civitas.celestis.listener.object;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.number.Vector;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>ObjectsCollidedListener</h2>
 * <p>Handles processing collisions.</p>
 */
public final class ObjectsCollidedListener implements Listener {
    @EventHandler(priority = EventHandler.Priority.POST_LATE)
    public void onObjectsCollided(@Nonnull ObjectsCollidedEvent event) {
        if (event.isCancelled()) return; // Respect cancellation

        final PhysicalObject o1 = event.getObjects().first();
        final PhysicalObject o2 = event.getObjects().second();

        final Pair<Vector> v = getFinalVelocities(o1.getAcceleration(), o2.getAcceleration(), o1.getMass(), o2.getMass());

        o1.setAcceleration(v.first());
        o2.setAcceleration(v.second());
    }

    @Nonnull
    private Pair<Vector> getFinalVelocities(
            @Nonnull Vector u1,
            @Nonnull Vector u2,
            @Nonnegative double m1,
            @Nonnegative double m2
    ) {
        // Catch potential arithmetic exception
        if (m1 + m2 == 0) return new Pair<>(Vector.ZERO, Vector.ZERO);

        return new Pair<>(
                u1.multiply(m1 - m2).divide(m1 + m2).add(u2.multiply(2 * m2).divide(m1 + m2)),
                u1.multiply(2 * m1).divide(m1 + m2).add(u2.multiply(m2 - m1).divide(m1 + m2))
        );
    }

}
