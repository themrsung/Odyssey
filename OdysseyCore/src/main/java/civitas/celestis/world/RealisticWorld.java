package civitas.celestis.world;

import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>RealisticWorld</h2>
 * <p>A world with gravity and air resistance.</p>
 */
public interface RealisticWorld extends World {
    /**
     * Gets the gravity of this world.
     *
     * @return Gravity
     */
    @Nonnull
    Vector getGravity();

    /**
     * Gets the gravitational acceleration of this world.
     *
     * @return Gravitational acceleration
     */
    @Nonnegative
    double getGravitationalAcceleration();

    /**
     * Gets the air density of this world.
     *
     * @return Air density
     */
    @Nonnegative
    double getAirDensity();

    /**
     * Sets the gravity of this world.
     *
     * @param gravity Gravity
     */
    void setGravity(@Nonnull Vector gravity);

    /**
     * Sets the air density of this world.
     *
     * @param density Air density
     */
    void setAirDensity(@Nonnegative double density);
}
