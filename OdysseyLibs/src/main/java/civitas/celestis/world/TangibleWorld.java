package civitas.celestis.world;

import civitas.celestis.number.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>TangibleWorld</h2>
 * <p>A tangible world has gravity and air resistance.</p>
 */
public interface TangibleWorld extends World {
    /**
     * Gets the gravity vector of this world.
     *
     * @return Gravity
     */
    @Nonnull
    Vector3 getGravity();

    /**
     * Gets the air density of this world.
     *
     * @return Air density
     */
    @Nonnegative
    double getAirDensity();

    /**
     * Sets the gravity vector of this world.
     *
     * @param gravity Gravity
     */
    void setGravity(@Nonnull Vector3 gravity);

    /**
     * Sets the air density of this world.
     *
     * @param density Air density
     */
    void setAirDensity(@Nonnegative double density);
}
