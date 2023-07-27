package civitas.celestis.world.lifecycle;

import civitas.celestis.world.World;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * <h2>WorldManager</h2>
 * <p>Manages the lifecycle of worlds.</p>
 */
public final class WorldManager {
    /**
     * Starts ticking worlds.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops ticking worlds.
     */
    public void stop() {
        thread.interrupt();
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
        for (World world : worlds) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NullPointerException("World of unique identifier " + uniqueId + " cannot be found.");
    }

    /**
     * Gets a world by name.
     *
     * @param name Name of world
     * @return World of name if found, {@code null} if not
     */
    @Nullable
    public World getWorld(@Nonnull String name) {
        for (World world : worlds) {
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
        tickTimes.put(world, DateTime.now());
    }

    /**
     * Removes a world from this manager.
     *
     * @param world World to remove
     */
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
        tickTimes.remove(world);
    }

    private final List<World> worlds = new ArrayList<>();
    private final Map<World, DateTime> tickTimes = new HashMap<>();
    private final Thread thread = new Thread(() -> {
        while (true) {
            List.copyOf(worlds).forEach(w -> {
                // Cache time for consistency
                final DateTime now = DateTime.now();
                final DateTime previous = tickTimes.getOrDefault(w, now);
                final Duration delta = new Duration(previous, now);

                w.tick(delta);
            });
        }
    });
}
