package civitas.celestis.event;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h2>EventHandler</h2>
 * <p>This annotation marks a method as an event handler.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Gets the priority of this event handler.
     *
     * @return {@link Priority}
     */
    @Nonnull
    Priority priority() default Priority.NORMAL;

    /**
     * The priority of an event handler.
     */
    enum Priority {
        EARLY,
        NORMAL,
        LATE;
    }
}
