package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.task.lifecycle.Scheduler;

import javax.annotation.Nonnull;

/**
 * <h2>Odyssey</h2>
 * <p>The main class of Odyssey.</p>
 */
public final class Odyssey {
    /**
     * Starts the engine.
     */
    public static void start() {

        // Start modules
        scheduler.start();
        eventManager.start();
    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        // Stop modules
        eventManager.stop();
        scheduler.stop();
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
     * Gets the event manager instance.
     *
     * @return {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    private static final Scheduler scheduler = new Scheduler();
    private static final EventManager eventManager = new EventManager();
}
