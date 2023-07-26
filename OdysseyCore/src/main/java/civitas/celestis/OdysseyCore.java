package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.player.lifecycle.PlayerManager;
import civitas.celestis.state.State;
import civitas.celestis.task.lifecycle.Scheduler;

import javax.annotation.Nonnull;

/**
 * <h2>OdysseyCore</h2>
 * <p>A static-architecture class which holds the core modules of Odyssey.</p>
 */
public final class OdysseyCore {
    //
    // Controls
    //

    /**
     * Starts the core modules of Odyssey.
     */
    public static void start() {
        eventManager.start();
        state.start();
        scheduler.start();
    }

    /**
     * Stops the core modules of Odyssey.
     */
    public static void stop() {
        eventManager.stop();
        state.stop();
        scheduler.stop();
    }

    //
    // Getters
    //

    /**
     * Gets the event manager instance.
     *
     * @return {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the scheduler instance.
     *
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Gets the game state.
     *
     * @return {@link State}
     */
    @Nonnull
    public static State getState() {
        return state;
    }

    /**
     * Gets the player manager instance.
     *
     * @return {@link PlayerManager}
     */
    @Nonnull
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    //
    // Modules
    //
    private static final EventManager eventManager = EventManager.getDefaultInstance();
    private static final Scheduler scheduler = Scheduler.getDefaultInstance();
    private static final State state = State.getEmptyState();
    private static final PlayerManager playerManager = PlayerManager.getDefaultInstance();
}
