package civitas.celestis.player.lifecycle;

import civitas.celestis.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h2>PlayerManagerImpl</h2>
 * <p>The default implementation of {@link PlayerManager}.</p>
 */
class PlayerManagerImpl implements PlayerManager {
    private final List<Player> players = new ArrayList<>();

    @Nonnull
    @Override
    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    @Nonnull
    @Override
    public Player getPlayer(@Nonnull UUID uniqueId) throws NullPointerException {
        for (Player player : players) {
            if (player.getUniqueId().equals(uniqueId)) return player;
        }

        throw new NullPointerException("Player of unique identifier " + uniqueId + " cannot be found.");
    }

    @Nullable
    @Override
    public Player getPlayer(@Nonnull String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) return player;
        }

        return null;
    }

    @Override
    public void addPlayer(@Nonnull Player player) throws IllegalArgumentException {
        if (players.contains(player)) throw new IllegalArgumentException("Player is already present in this session.");

        players.add(player);
    }

    @Override
    public void removePlayer(@Nonnull Player player) {
        players.remove(player);
    }
}
