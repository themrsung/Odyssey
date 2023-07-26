package civitas.celestis.state;

import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * <h2>State</h2>
 * <p>Represents the state of a game.</p>
 */
public interface State {
    /**
     * Gets a list of worlds in this state.
     *
     * @return List of worlds
     */
    @Nonnull
    List<World> getWorlds();

    /**
     * Gets a world by unique identifier.
     *
     * @param uniqueId Unique identifier of world
     * @return World of given unique identifier
     * @throws NullPointerException When a world of matching unique identifier cannot be found
     */
    @Nonnull
    World getWorld(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Gets a world by name.
     *
     * @param name Name of world
     * @return World if found, {@code null} if not
     */
    @Nullable
    World getWorld(@Nonnull String name);

    /**
     * Adds a world to this state.
     *
     * @param world World to add
     * @throws IllegalArgumentException When the world is already present in this state
     */
    void addWorld(@Nonnull World world) throws IllegalArgumentException;

    /**
     * Removes a world from this state.
     *
     * @param world World to remove
     */
    void removeWorld(@Nonnull World world);
}
