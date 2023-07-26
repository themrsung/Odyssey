package civitas.celestis.task;

import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Task</h2>
 * <p>Tasks can be executed in regular intervals.</p>
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta Duration between the last execution and now
     */
    void execute(@Nonnull Duration delta);

    /**
     * Gets the interval of this task.
     *
     * @return Interval
     */
    @Nonnull
    default Duration interval() {
        return Duration.ZERO;
    }
}
