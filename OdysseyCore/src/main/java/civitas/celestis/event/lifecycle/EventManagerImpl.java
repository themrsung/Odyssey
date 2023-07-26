package civitas.celestis.event.lifecycle;

import civitas.celestis.OdysseyCore;
import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;

import javax.annotation.Nonnull;
import java.util.*;

class EventManagerImpl implements EventManager {
    /**
     * The queue of events.
     */
    final Queue<Event> eventQueue = new LinkedList<>();

    /**
     * The list of listeners.
     */
    final List<Listener> listeners = new ArrayList<>();

    /**
     * Task to handle events.
     */
    private final EventTask task = new EventTask(this);

    @Override
    public void start() {
        OdysseyCore.getScheduler().registerTask(task);
    }

    @Override
    public void stop() {
        OdysseyCore.getScheduler().unregisterTask(task);
    }

    @Override
    public void registerListener(@Nonnull Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void registerListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::registerListener);
    }

    @Override
    public void registerListeners(@Nonnull List<Listener> listeners) {
        listeners.forEach(this::registerListener);
    }

    @Override
    public void unregisterListener(@Nonnull Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void unregisterListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::unregisterListener);
    }

    @Override
    public void unregisterListeners(@Nonnull List<Listener> listeners) {
        listeners.forEach(this::unregisterListener);
    }
}
