package civitas.celestis.object;

import civitas.celestis.number.Vector;
import civitas.celestis.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>Character</h2>
 * <p>An object which can be possessed by a player.</p>
 */
public interface Character extends OdysseyObject {
    /**
     * Gets the player controlling this character.
     *
     * @return Player
     */
    @Nullable
    Player getPlayer();

    /**
     * Gets the current viewport offset of this character.
     *
     * @return Viewport offset
     */
    @Nonnull
    Vector getViewportOffset();

    /**
     * Sets the player controlling this character.
     *
     * @param player Player
     */
    void setPlayer(@Nullable Player player);

    /**
     * Sets the viewport offset of this character.
     *
     * @param offset Viewport offset
     */
    void setViewportOffset(@Nonnull Vector offset);
}
