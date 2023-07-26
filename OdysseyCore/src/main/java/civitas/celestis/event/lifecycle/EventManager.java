package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Listener;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>EventManager</h2>
 * <p>Handles the execution of events.</p>
 */
public interface EventManager {
    /**
     * Gets a new default event manager instance.
     *
     * @return New {@link EventManager} instance
     */
    @Nonnull
    static EventManager getDefaultInstance() {
        return new EventManagerImpl();
    }

    /**
     * Starts processing events.
     */
    void start();

    /**
     * Stops processing events.
     */
    void stop();

    /**
     * Registers a listener to this event manager.
     *
     * @param listener Listener to register
     */
    void registerListener(@Nonnull Listener listener);

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners Listeners to register
     */
    void registerListeners(@Nonnull Listener... listeners);

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners List of listeners to register
     */
    void registerListeners(@Nonnull List<Listener> listeners);

    /**
     * Unregisters a listener from this event manager.
     *
     * @param listener Listener to unregister
     */
    void unregisterListener(@Nonnull Listener listener);

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners Listeners to unregister
     */
    void unregisterListeners(@Nonnull Listener... listeners);

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners List of listeners to unregister
     */
    void unregisterListeners(@Nonnull List<Listener> listeners);
}
