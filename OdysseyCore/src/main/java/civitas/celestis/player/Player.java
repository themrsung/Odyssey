package civitas.celestis.player;

import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.object.Character;
import civitas.celestis.util.Identifiable;

import javax.annotation.Nonnull;

/**
 * <h2>Player</h2>
 * <p>Represents an in-game player.</p>
 */
public interface Player extends Identifiable {
    //
    // Identification
    //

    /**
     * Gets the name of this player.
     *
     * @return Name of player
     */
    @Nonnull
    String getName();

    /**
     * Gets the character this player is controlling.
     *
     * @return Character of player
     */
    @Nonnull
    Character getCharacter();

    /**
     * Sets the character this player is controlling.
     *
     * @param character Character of player
     */
    void setCharacter(@Nonnull Character character);

    //
    // Viewport
    //

    /**
     * Gets the current discrete viewport origin of this player.
     *
     * @return Viewport origin
     */
    @Nonnull
    Vector getViewportOrigin();

    /**
     * Gets the angle of this player's viewport.
     *
     * @return Angle of viewport
     */
    @Nonnull
    Quaternion getViewportAngle();

    //
    // Character Delegates
    //

    /**
     * Gets the location of this player.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the acceleration of this player.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector getAcceleration();

    /**
     * Gets the rotation of this player.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the rate of rotation of this player.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Sets the location of this player.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the acceleration of this player.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rotation of this player.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this player.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    /**
     * Moves this player by given amount.
     *
     * @param amount Amount to move
     */
    void move(@Nonnull Vector amount);

    /**
     * Accelerates this player by given amount.
     *
     * @param amount Acceleration to apply
     */
    void accelerate(@Nonnull Vector amount);

    /**
     * Rotates this player by given amount.
     *
     * @param rotation Rotation to apply
     */
    void rotate(@Nonnull Quaternion rotation);
}
