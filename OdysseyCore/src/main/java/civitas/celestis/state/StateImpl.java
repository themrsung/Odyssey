package civitas.celestis.state;

import civitas.celestis.OdysseyCore;
import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class StateImpl implements State {
    /**
     * The list of worlds.
     */
    private final List<World> worlds = new ArrayList<>();

    /**
     * The task to tick objects.
     */
    private final StateTickTask task = new StateTickTask(this);

    @Override
    public void start() {
        OdysseyCore.getScheduler().registerTask(task);
    }

    @Override
    public void stop() {
        OdysseyCore.getScheduler().unregisterTask(task);
    }

    @Nonnull
    @Override
    public List<World> getWorlds() {
        return List.copyOf(worlds);
    }

    @Nonnull
    @Override
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (World world : worlds) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NullPointerException("World with unique identifier " + uniqueId + " cannot be found.");
    }

    @Nullable
    @Override
    public World getWorld(@Nonnull String name) {
        for (World world : worlds) {
            if (world.getName().equals(name)) return world;
        }

        return null;
    }

    @Override
    public void addWorld(@Nonnull World world) throws IllegalArgumentException {
        if (worlds.contains(world)) throw new IllegalArgumentException("State already contains given world.");

        worlds.add(world);
    }

    @Override
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    @Override
    public void save(@Nonnull String path) throws IOException {
        // TODO implement
    }

    @Override
    public void load(@Nonnull String path) throws IOException {

    }
}
