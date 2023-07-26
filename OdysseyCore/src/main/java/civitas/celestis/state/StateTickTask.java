package civitas.celestis.state;

import civitas.celestis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>StateTickTask</h2>
 * <p>A task which ticks every object in a state.</p>
 */
public class StateTickTask implements Task {
    StateTickTask(@Nonnull StateImpl parent) {
        this.parent = parent;
    }

    private final StateImpl parent;

    @Override
    public void execute(@Nonnull Duration delta) {
        parent.getWorlds().forEach(w -> w.tick(delta));
    }

    @Nonnull
    @Override
    public Duration interval() {
        return new Duration(1);
    }
}
