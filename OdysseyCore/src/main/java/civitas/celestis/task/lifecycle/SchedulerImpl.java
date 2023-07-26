package civitas.celestis.task.lifecycle;

import civitas.celestis.event.lifecycle.EventTask;
import civitas.celestis.state.StateTickTask;
import civitas.celestis.task.Task;
import civitas.celestis.util.Counter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * <h2>SchedulerImpl</h2>
 * <p>Default implementation of {@link Scheduler}.</p>
 */
class SchedulerImpl implements Scheduler {
    /**
     * The scheduler core dedicated to the event manager.
     */
    private final SchedulerCore eventCore = new SchedulerCore(1);

    /**
     * The scheduler core dedicated to ticking objects in the state.
     */
    private final SchedulerCore stateCore = new SchedulerCore(1);

    /**
     * Asynchronous scheduler cores.
     */
    private final SchedulerCore[] asyncCores = {
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1),
            new SchedulerCore(1)
    };

    /**
     * Counter to distribute tasks.
     */
    private final Counter asyncCounter = new Counter(asyncCores.length);

    //
    // Implementations
    //


    @Override
    public void start() {
        eventCore.start();
        Arrays.stream(asyncCores).forEach(SchedulerCore::start);
    }

    @Override
    public void stop() {
        eventCore.stop();
        Arrays.stream(asyncCores).forEach(SchedulerCore::stop);
    }

    @Override
    public void registerTask(@Nonnull Task task) {
        if (task instanceof EventTask) {
            eventCore.addTask(task);
        } else if (task instanceof StateTickTask) {
            stateCore.addTask(task);
        } else {
            asyncCores[asyncCounter.get()].addTask(task);
        }
    }

    @Override
    public void registerTasks(@Nonnull Task... tasks) {
        final int i = asyncCounter.get();
        Arrays.stream(tasks).forEach(t -> asyncCores[i].addTask(t));
    }

    @Override
    public void registerTasks(@Nonnull List<Task> tasks) {
        final int i = asyncCounter.get();
        tasks.forEach(t -> asyncCores[i].addTask(t));
    }

    @Override
    public void unregisterTask(@Nonnull Task task) {
        if (task instanceof EventTask) {
            eventCore.removeTask(task);
        } else if (task instanceof StateTickTask) {
            stateCore.removeTask(task);
        } else {
            Arrays.stream(asyncCores).forEach(c -> c.removeTask(task));
        }
    }

    @Override
    public void unregisterTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::unregisterTask);
    }

    @Override
    public void unregisterTasks(@Nonnull List<Task> tasks) {
        tasks.forEach(this::unregisterTask);
    }
}
