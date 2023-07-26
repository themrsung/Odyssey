package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.io.Deserializer;
import civitas.celestis.state.State;
import civitas.celestis.task.lifecycle.Scheduler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;

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
        scheduler.start();
    }

    /**
     * Stops the core modules of Odyssey.
     */
    public static void stop() {
        eventManager.stop();
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

    //
    // Modules
    //
    private static final EventManager eventManager = EventManager.getDefaultInstance();
    private static final Scheduler scheduler = Scheduler.getDefaultInstance();
}
