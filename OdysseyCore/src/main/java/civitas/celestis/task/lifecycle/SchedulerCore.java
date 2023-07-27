package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>SchedulerCore</h2>
 * <p>An internal core of a scheduler.</p>
 */
public final class SchedulerCore {
    @SuppressWarnings("BusyWait")
    public SchedulerCore(@Nonnegative long accuracy) {
        this.tasks = new ArrayList<>();
        this.executionTimes = new HashMap<>();
        this.thread = new Thread(() -> {
            while (true) {
                List.copyOf(tasks).forEach(t -> {
                    // Cache current time for consistency
                    final DateTime now = DateTime.now();
                    final DateTime previous = executionTimes.getOrDefault(t, now);
                    final Duration delta = new Duration(previous, now);

                    // Respect interval
                    if (delta.isShorterThan(t.getInterval())) return;

                    // Execute task and keep time
                    t.execute(delta);
                    executionTimes.put(t, now);
                });

                try {
                    Thread.sleep(accuracy);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "SchedulerCore");
    }

    /**
     * Registers a task to this core.
     *
     * @param task Task to register
     */
    public void registerTask(@Nonnull Task task) {
        tasks.add(task);
        executionTimes.put(task, DateTime.now());
    }

    /**
     * Unregisters a task from this core.
     *
     * @param task Task to unregister
     */
    public void unregisterTask(@Nonnull Task task) {
        tasks.remove(task);
        executionTimes.remove(task);
    }

    /**
     * Starts this core.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops this core.
     */
    public void stop() {
        thread.interrupt();
    }

    private final List<Task> tasks;
    private final Map<Task, DateTime> executionTimes;
    private final Thread thread;
}
