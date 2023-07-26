package civitas.celestis.world;

import civitas.celestis.OdysseyCore;
import civitas.celestis.event.object.ObjectCollisionEvent;
import civitas.celestis.number.Vector;
import civitas.celestis.object.OdysseyObject;
import civitas.celestis.util.Pair;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h2>AbstractWorld</h2>
 * <p>An adapter class for {@link World}.</p>
 */
public abstract class AbstractWorld implements World {
    /**
     * All-args constructor.
     *
     * @param uniqueId           Unique identifier of this world
     * @param name               Name of this world
     * @param objects            List of objects in this world
     * @param overlappingObjects List of overlapping object pairs in this world
     * @param gravity            Gravity vector of this world
     * @param airDensity         Air density of this world
     */
    public AbstractWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<OdysseyObject> objects,
            @Nonnull List<Pair<OdysseyObject>> overlappingObjects,
            @Nonnull Vector gravity,
            @Nonnegative double airDensity
    ) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = objects;
        this.overlappingObjects = overlappingObjects;
        this.gravity = gravity;
        this.airDensity = airDensity;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    private final List<OdysseyObject> objects;
    @Nonnull
    protected final List<Pair<OdysseyObject>> overlappingObjects;
    @Nonnull
    private Vector gravity;
    @Nonnegative
    private double airDensity;

    @Override
    public void tick(@Nonnull Duration delta) {
        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Get all possible pairs of objects
        final List<Pair<OdysseyObject>> pairs = Pair.of(objects);

        // Clean invalid cache
        overlappingObjects.removeIf(p -> !pairs.contains(p));

        // Handle collisions
        pairs.forEach(p -> {
            final OdysseyObject o1 = p.first();
            final OdysseyObject o2 = p.second();

            if (o1.overlaps(o2)) {
                if (!overlappingObjects.contains(p)) {
                    OdysseyCore.getEventManager().callEvent(new ObjectCollisionEvent(p));
                    overlappingObjects.add(p);
                }
            } else {
                overlappingObjects.remove(p);
            }
        });

        // Scale gravity
        final Vector gravity = this.gravity.multiply(seconds);

        objects.forEach(o -> {
            // Apply gravity
            o.accelerate(gravity);

            // Get fluid density
            final AtomicReference<Double> fluidDensity = new AtomicReference<>(airDensity);
            overlappingObjects.forEach(pair -> {
                if (!pair.contains(o)) return;
                final OdysseyObject other = pair.other(o);
                fluidDensity.getAndUpdate(d -> Math.max(d, other.getDensity()));
            });

            // Calculate drag force
            final double dragForce = o.getDragCoefficient()
                    * fluidDensity.get()
                    * o.getCrossSection()
                    * Math.pow(o.getVelocity(), 2);

            // Apply drag force
            if (Double.isFinite(dragForce) && dragForce > 0) {
                final double kineticEnergy = 0.5 * o.getMass() * o.getVelocity();
                if (kineticEnergy > 0) {
                    final double decelerationRatio = Math.max(Math.min(1, 1 - ((dragForce * seconds) / kineticEnergy)), 0);
                    o.setAcceleration(o.getAcceleration().multiply(decelerationRatio));
                }
            }

            // Tick object
            o.tick(delta);
        });
    }

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public List<OdysseyObject> getObjects() {
        return List.copyOf(objects);
    }

    @Nonnull
    @Override
    public OdysseyObject getObject(@Nonnull UUID uniqueId) throws NullPointerException {
        for (OdysseyObject object : objects) {
            if (object.getUniqueId().equals(uniqueId)) return object;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
    }

    @Override
    @Nonnull
    public Vector getGravity() {
        return gravity;
    }

    @Override
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public void addObject(@Nonnull OdysseyObject object) throws IllegalArgumentException {
        if (objects.contains(object)) throw new IllegalArgumentException("Object is already present in world.");
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull OdysseyObject object) {
        objects.remove(object);
    }

    @Override
    public void setGravity(@Nonnull Vector gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }
}
