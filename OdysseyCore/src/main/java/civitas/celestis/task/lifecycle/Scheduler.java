package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Scheduler</h2>
 * <p>Schedulers handle the regular execution of tasks.</p>
 */
public interface Scheduler {
    /**
     * Gets a new default instance.
     *
     * @return New {@link Scheduler} instance
     */
    @Nonnull
    static Scheduler getDefaultInstance() {
        return new SchedulerImpl();
    }

    /**
     * Starts the scheduler.
     */
    void start();

    /**
     * Stops the scheduler.
     */
    void stop();

    /**
     * Registers a task to this scheduler.
     *
     * @param task Task to register
     */
    void registerTask(@Nonnull Task task);

    /**
     * Registers multiple tasks to this scheduler.
     * All tasks will be registered to a single thread.
     *
     * @param tasks Tasks to register
     */
    void registerTasks(@Nonnull Task... tasks);

    /**
     * Registers multiple tasks to this scheduler.
     * All tasks will be registered to a single thread.
     *
     * @param tasks List of tasks to register
     */
    void registerTasks(@Nonnull List<Task> tasks);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    void unregisterTask(@Nonnull Task task);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Tasks to unregister
     */
    void unregisterTasks(@Nonnull Task... tasks);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks List of tasks to unregister
     */
    void unregisterTasks(@Nonnull List<Task> tasks);
}
