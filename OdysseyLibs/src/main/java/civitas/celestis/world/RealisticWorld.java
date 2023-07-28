package civitas.celestis.world;

import civitas.celestis.number.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.TangibleObject;
import civitas.celestis.util.Pair;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h2>RealisticWorld</h2>
 * <p>The default implementation of {@link TangibleWorld}.</p>
 */
public class RealisticWorld extends AbstractWorld implements TangibleWorld {
    /**
     * Creates a new world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull Vector3 gravity,
            @Nonnegative double airDensity
    ) {
        this(uniqueId, name, new ArrayList<>(), gravity, airDensity);
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param objects    List of objects in this world
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull Vector3 gravity,
            @Nonnegative double airDensity
    ) {
        this(uniqueId, name, objects, gravity, airDensity, new ArrayList<>());
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param objects    List of objects in this world
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     * @param overlaps   List of overlapping object pairs in this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull Vector3 gravity,
            @Nonnegative double airDensity,
            @Nonnull List<Pair<TangibleObject>> overlaps
    ) {
        super(uniqueId, name, objects);
        this.gravity = gravity;
        this.airDensity = airDensity;
        this.overlaps = overlaps;
    }

    @Nonnull
    private Vector3 gravity;
    @Nonnegative
    private double airDensity;
    @Nonnull
    private final List<Pair<TangibleObject>> overlaps;

    @Override
    public void tick(@Nonnull Duration delta) {
        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Scale gravity
        final Vector3 g = gravity.multiply(seconds);

        // Get all possible pairs
        final List<Pair<TangibleObject>> pairs = Pair.of(getObjects(TangibleObject.class).toList());

        // Clear invalid cache
        overlaps.removeIf(p -> !pairs.contains(p));

        // Loop through pairs
        pairs.forEach(p -> {
            final TangibleObject o1 = p.first();
            final TangibleObject o2 = p.second();

            if (o1.overlaps(o2)) {
                if (!overlaps.contains(p)) {
                    overlaps.add(p);

                    // Handle collision
                    final Vector3 u1 = o1.getAcceleration();
                    final Vector3 u2 = o2.getAcceleration();

                    final double m1 = o1.getMass();
                    final double m2 = o2.getMass();

                    if (m1 + m2 == 0) {
                        // Calculating velocities will invoke an exception
                        o1.setAcceleration(Vector3.ZERO);
                        o2.setAcceleration(Vector3.ZERO);
                    } else {
                        final Vector3 v1 = u1.multiply(m1 - m2).divide(m1 + m2).add(u2.multiply(2 * m2).divide(m1 + m2));
                        final Vector3 v2 = u1.multiply(2 * m1).divide(m1 + m2).add(u2.multiply(m2 - m1).divide(m1 + m2));

                        o1.setAcceleration(v1);
                        o2.setAcceleration(v2);
                    }
                }
            } else {
                overlaps.remove(p);
            }
        });


        // Loop through objects
        getObjects().forEach(o -> {
            // Apply gravity
            o.accelerate(g);

            if (o instanceof TangibleObject to) {
                // Apply fluid resistance
                final AtomicReference<Double> fluidDensity = new AtomicReference<>(airDensity);
                overlaps.forEach(p -> {
                    if (!p.contains(to)) return;
                    fluidDensity.getAndUpdate(d -> Math.max(d, p.other(to).getDensity()));
                });

                final double dragForce = to.getDragCoefficient()
                        * fluidDensity.get()
                        * to.getCrossSection()
                        * to.getAcceleration().magnitude2();

                // Filter out illegal values
                if (!Double.isFinite(dragForce)) return;
                if (dragForce <= 0) return;

                final double kineticEnergy = 0.5 * to.getMass() * to.getAcceleration().magnitude();
                if (kineticEnergy == 0) return; // No need to apply resistance

                final double decelerationRatio = Math.max(Math.min(1, 1 - ((dragForce * seconds) / kineticEnergy)), 0);
                o.setAcceleration(o.getAcceleration().multiply(decelerationRatio));
            }

            // Tick object
            o.tick(delta);
        });
    }

    @Override
    @Nonnull
    public Vector3 getGravity() {
        return gravity;
    }

    @Override
    @Nonnegative
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public void setGravity(@Nonnull Vector3 gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(@Nonnegative double airDensity) {
        this.airDensity = airDensity;
    }
}
