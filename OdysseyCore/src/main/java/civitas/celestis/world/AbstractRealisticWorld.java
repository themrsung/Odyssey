package civitas.celestis.world;

import civitas.celestis.number.Vector;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;
import civitas.celestis.world.AbstractWorld;
import civitas.celestis.world.RealisticWorld;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h2>AbstractRealisticWorld</h2>
 * <p>An abstract world with default physics behavior.</p>
 */
public abstract class AbstractRealisticWorld extends AbstractWorld implements RealisticWorld {
    /**
     * Creates a new abstract realistic world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name Name of this world
     * @param objects List of objects in this world
     * @param overlaps List of overlapping object pairs
     * @param gravity Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public AbstractRealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull List<Pair<PhysicalObject>> overlaps,
            @Nonnull Vector gravity,
            @Nonnegative double airDensity
    ) {
        super(uniqueId, name, objects, overlaps);
        this.gravity = gravity;
        this.airDensity = airDensity;
    }

    @Nonnull
    private Vector gravity;
    @Nonnegative
    private double airDensity;

    @Override
    public void tick(@Nonnull Duration delta) {
        super.tick(delta);

        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Scale gravity
        final Vector gravity = this.gravity.multiply(seconds);

        // Loop through physical objects
        objects.stream().filter(PhysicalObject.class::isInstance).map(PhysicalObject.class::cast).forEach(o -> {
            // Apply gravity
            o.accelerate(gravity);

            // Apply drag force
            final AtomicReference<Double> fluidDensity = new AtomicReference<>(airDensity);
            overlaps.forEach(pair -> {
                if (!pair.contains(o)) return;
                fluidDensity.getAndUpdate(d -> Math.max(d, pair.other(o).getDensity()));
            });

            final double dragForce = o.getDragCoefficient()
                    * fluidDensity.get()
                    * o.getCrossSection()
                    * o.getVelocity2();

            // Filter invalid values
            if (!Double.isFinite(dragForce) || dragForce <= 0) return;

            final double kineticEnergy = 0.5 * o.getMass() * o.getVelocity();
            if (kineticEnergy > 0) {
                final double decelerationRatio = Math.max(Math.min(1, 1 - ((dragForce * seconds) / kineticEnergy)), 0);
                o.setAcceleration(o.getAcceleration().multiply(decelerationRatio));
            }
        });
    }

    @Override
    @Nonnull
    public Vector getGravity() {
        return gravity;
    }

    @Override
    public double getGravitationalAcceleration() {
        return gravity.magnitude();
    }

    @Override
    @Nonnegative
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public void setGravity(@Nonnull Vector gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(@Nonnegative double airDensity) {
        this.airDensity = airDensity;
    }
}
