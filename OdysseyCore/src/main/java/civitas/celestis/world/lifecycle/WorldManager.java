package civitas.celestis.world.lifecycle;

import civitas.celestis.Odyssey;
import civitas.celestis.task.Task;
import civitas.celestis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h2>WorldManager</h2>
 * <p>Handles the lifecycle of worlds.</p>
 */
public final class WorldManager {
    /**
     * Starts ticking worlds.
     */
    public void start() {
        Odyssey.getScheduler().registerTask(worldTicker);
    }

    /**
     * Stops ticking worlds.
     */
    public void stop() {
        Odyssey.getScheduler().unregisterTask(worldTicker);
    }

    /**
     * Gets a list of worlds.
     *
     * @return List of worlds
     */
    @Nonnull
    public List<World> getWorlds() {
        return new ArrayList<>(worlds);
    }

    /**
     * Gets a world by unique identifier.
     *
     * @param uniqueId Unique identifier of world
     * @return World of unique identifier
     * @throws NullPointerException When a world of unique identifier cannot be found
     */
    @Nonnull
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (World world : getWorlds()) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NullPointerException("World with unique identifier " + uniqueId + " cannot be found.");
    }

    /**
     * Gets a world by name.
     *
     * @param name Name of world
     * @return World of name if found, {@code null} if not
     */
    @Nullable
    public World getWorld(@Nonnull String name) {
        for (World world : getWorlds()) {
            if (world.getName().equals(name)) return world;
        }

        return null;
    }

    /**
     * Adds a world to this manager.
     *
     * @param world World to add
     */
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
    }

    /**
     * Removes a world from this manager.
     *
     * @param world World to remove
     */
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    private final List<World> worlds = new ArrayList<>();
    private final Task worldTicker = new Task() {
        @Override
        public void execute(@Nonnull Duration delta) {
            getWorlds().forEach(w -> w.tick(delta));
        }

        @Nonnull
        @Override
        public Duration getInterval() {
            return ONE_MILLISECOND;
        }
    };
}
