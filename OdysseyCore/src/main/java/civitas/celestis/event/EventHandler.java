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
     * Gets the priority of this handler.
     *
     * @return {@link Priority}
     */
    @Nonnull
    Priority priority() default Priority.NORMAL;

    enum Priority {
        PRE_EARLY, // Called first
        EARLY,
        POST_EARLY,
        PRE_NORMAL,
        NORMAL,
        POST_NORMAL,
        PRE_LATE,
        LATE,
        POST_LATE,
        PRE_MONITOR,
        MONITOR,
        POST_MONITOR; // Called last
    }
}
