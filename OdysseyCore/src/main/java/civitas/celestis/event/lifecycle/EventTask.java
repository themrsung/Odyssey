package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import civitas.celestis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h2>EventTask</h2>
 * <p>A task which processes queued events.</p>
 */
public final class EventTask implements Task {
    EventTask(@Nonnull EventManagerImpl parent) {
        this.parent = parent;
    }

    @Nonnull
    private final EventManagerImpl parent;

    @Override
    public void execute(@Nonnull Duration delta) {
        while (!parent.eventQueue.isEmpty()) {
            final Event event = parent.eventQueue.poll();
            if (event != null) {
                final List<HandlerMethod> handlers = new ArrayList<>();

                // Copy list to prevent concurrent modification
                List.copyOf(parent.listeners)
                        .forEach(l -> Arrays.stream(l.getClass().getDeclaredMethods())
                                .forEach(m -> {
                                    if (m.getParameters().length != 1) return;
                                    if (!m.isAnnotationPresent(EventHandler.class)) return;
                                    if (!m.getParameters()[0].getType().isAssignableFrom(event.getClass())) return;

                                    handlers.add(new HandlerMethod(l, m));
                                }));

                // Respect priorities
                handlers.sort(Comparator.comparing(h -> h.handler().getAnnotation(EventHandler.class).priority()));

                // Call handlers
                handlers.forEach(h -> {
                    try {
                        h.handler().invoke(h.listener(), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    /**
     * A transient data class for storing handler methods.
     *
     * @param listener Listener instance
     * @param handler  Handler method
     */
    private record HandlerMethod(
            @Nonnull Listener listener,
            @Nonnull Method handler
    ) {}
}
