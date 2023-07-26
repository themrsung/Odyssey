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
 * <p>A scheduler core.</p>
 */
class SchedulerCore {
    @SuppressWarnings("BusyWait")
    SchedulerCore(@Nonnegative int accuracy) {
        this.thread = new Thread(() -> {
            final Map<Task, DateTime> executionTimes = new HashMap<>();

            while (true) {
                // Add cached tasks
                if (!addCache.isEmpty()) {
                    tasks.addAll(addCache);
                    addCache.clear();
                }

                // Remove cached tasks
                if (!removeCache.isEmpty()) {
                    tasks.removeAll(removeCache);
                    removeCache.forEach(executionTimes::remove);
                    removeCache.clear();
                }

                // Loop through tasks
                new ArrayList<>(tasks).forEach(t -> {
                    final DateTime now = DateTime.now(); // Cache time for consistency
                    final DateTime previous;

                    // Get last time or put now
                    if (executionTimes.containsKey(t)) {
                        previous = executionTimes.get(t);
                    } else {
                        executionTimes.put(t, now);
                        previous = now;
                    }

                    // Calculate delta
                    final Duration delta = new Duration(previous, now);

                    // Respect interval
                    if (delta.isShorterThan(t.interval())) return;

                    // Execute task and keep time
                    t.execute(delta);
                    executionTimes.put(t, now);
                });

                // Sleep
                try {
                    Thread.sleep(accuracy);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
    }

    void addTask(@Nonnull Task task) {
        addCache.add(task);
    }

    void removeTask(@Nonnull Task task) {
        if (!addCache.remove(task)) {
            removeCache.add(task);
        }
    }

    void start() {
        thread.start();
    }

    void stop() {
        thread.interrupt();
    }

    private final List<Task> tasks = new ArrayList<>();
    private final List<Task> addCache = new ArrayList<>();
    private final List<Task> removeCache = new ArrayList<>();

    private final Thread thread;
}
