package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <h2>EventManager</h2>
 * <p>Handles the lifecycle of events.</p>
 */
public final class EventManager {
    /**
     * Starts processing events.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops processing events.
     */
    public void stop() {
        thread.interrupt();
    }

    /**
     * Calls an event to be handled.
     *
     * @param event Event to call
     * @param <E>   Type of event to call
     */
    public <E extends Event> void call(@Nonnull E event) {
        eventQueue.add(event);
    }

    /**
     * Registers a listener to this event manager.
     *
     * @param listener Listener to register
     */
    public void registerListener(@Nonnull Listener listener) {
        listeners.add(listener);
    }

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners Listeners to register
     */
    public void registerListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::registerListener);
    }

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners List of listeners to register
     */
    public void registerListeners(@Nonnull List<Listener> listeners) {
        listeners.forEach(this::registerListener);
    }

    /**
     * Unregisters a listener from this event manager.
     *
     * @param listener Listener to unregister
     */
    public void unregisterListener(@Nonnull Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners Listeners to unregister
     */
    public void unregisterListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::unregisterListener);
    }

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners List of listeners to unregister
     */
    public void unregisterListeners(@Nonnull List<Listener> listeners) {
        listeners.forEach(this::unregisterListener);
    }

    private final Queue<Event> eventQueue = new LinkedList<>();
    private final List<Listener> listeners = new ArrayList<>();
    @SuppressWarnings("BusyWait")
    private final Thread thread = new Thread(() -> {
        while (true) {
            final Event event = eventQueue.poll();
            if (event != null) {
                final List<Handler> handlers = new ArrayList<>();

                List.copyOf(listeners).forEach(l -> Arrays.stream(l.getClass().getDeclaredMethods()).forEach(m -> {
                    if (!m.isAnnotationPresent(EventHandler.class)) return;
                    if (m.getParameterCount() != 1) return;
                    if (!m.getParameters()[0].getType().isAssignableFrom(event.getClass())) return;

                    handlers.add(new Handler(l, m));
                }));

                // Respect priorities
                handlers.sort(Comparator.comparing(h -> h.method().getAnnotation(EventHandler.class).priority()));
                handlers.forEach(h -> {
                    try {
                        // Call handler
                        h.method().invoke(h.listener(), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                return;
            }
        }
    });

    /**
     * A transient data class for storing handler methods.
     *
     * @param listener Listener object
     * @param method   Handler method
     */
    private record Handler(
            @Nonnull Listener listener,
            @Nonnull Method method
    ) {}
}
