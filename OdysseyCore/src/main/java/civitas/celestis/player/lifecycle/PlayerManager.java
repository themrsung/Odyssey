package civitas.celestis.player.lifecycle;

import civitas.celestis.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * <h2>PlayerManager</h2>
 * <p>Manages the players in this session.</p>
 */
public interface PlayerManager {
    /**
     * Gets a new default instance.
     *
     * @return New {@link PlayerManager} instance
     */
    @Nonnull
    static PlayerManager getDefaultInstance() {
        return new PlayerManagerImpl();
    }

    /**
     * Gets a list of all players in this session.
     *
     * @return List of players
     */
    @Nonnull
    List<Player> getPlayers();

    /**
     * Gets a player by unique identifier.
     *
     * @param uniqueId Unique identifier of player
     * @return Player of unique identifier
     * @throws NullPointerException When the player of unique identifier cannot be found
     */
    @Nonnull
    Player getPlayer(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Gets a player by name.
     *
     * @param name Name of player
     * @return Player of name if found, {@code null} if not
     */
    @Nullable
    Player getPlayer(@Nonnull String name);

    /**
     * Adds a player to this session.
     *
     * @param player Player to add
     * @throws IllegalArgumentException If the player is already present in this session
     */
    void addPlayer(@Nonnull Player player) throws IllegalArgumentException;

    /**
     * Removes a player from this session.
     *
     * @param player Player to remove
     */
    void removePlayer(@Nonnull Player player);
}
