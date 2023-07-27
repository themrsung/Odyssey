package civitas.celestis.task;

import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Task</h2>
 * <p>Tasks can be executed regularly by a scheduler.</p>
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta Duration between the last execution and now
     */
    void execute(@Nonnull Duration delta);

    /**
     * Gets the interval of this task the scheduler should aim to respect.
     *
     * @return Interval
     */
    @Nonnull
    default Duration getInterval() {return IMMEDIATE;}

    //
    // Constants
    //

    /**
     * Set interval to this to have no interval.
     */
    Duration IMMEDIATE = Duration.ZERO;

    /**
     * Set interval to 1ms for time-sensitive tasks. (Tasks which require duration to be non-zero)
     */
    Duration ONE_MILLISECOND = new Duration(1);
}
