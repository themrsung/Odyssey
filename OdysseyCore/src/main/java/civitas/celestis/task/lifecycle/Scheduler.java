package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.Counter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * <h2>Scheduler</h2>
 * <p>A scheduler handles the lifecycle of tasks.</p>
 */
public final class Scheduler {
    /**
     * Starts this scheduler.
     */
    public void start() {
        Arrays.stream(cores).forEach(SchedulerCore::start);
    }

    /**
     * Stops this scheduler.
     */
    public void stop() {
        Arrays.stream(cores).forEach(SchedulerCore::stop);
    }

    /**
     * Registers a task to this scheduler.
     *
     * @param task Task to register
     */
    public void registerTask(@Nonnull Task task) {
        cores[distributor.get()].registerTask(task);
    }

    /**
     * Registers multiple tasks to this scheduler.
     * All given tasks will be registered to a single thread.
     *
     * @param tasks Tasks to register
     */
    public void registerTasks(@Nonnull Task... tasks) {
        final int index = distributor.get();
        Arrays.stream(tasks).forEach(t -> cores[index].registerTask(t));
    }

    /**
     * Registers multiple tasks to this scheduler.
     * All given tasks will be registered to a single thread.
     *
     * @param tasks List of tasks to register
     */
    public void registerTasks(@Nonnull List<Task> tasks) {
        final int index = distributor.get();
        tasks.forEach(t -> cores[index].registerTask(t));
    }

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    public void unregisterTask(@Nonnull Task task) {
        Arrays.stream(cores).forEach(c -> c.unregisterTask(task));
    }

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Tasks to unregister
     */
    public void unregisterTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::unregisterTask);
    }

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks List of tasks to unregister
     */
    public void unregisterTasks(@Nonnull List<Task> tasks) {
        tasks.forEach(this::unregisterTask);
    }

    private final SchedulerCore[] cores = {
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1)
    };
    private final Counter distributor = new Counter(cores.length);
}
