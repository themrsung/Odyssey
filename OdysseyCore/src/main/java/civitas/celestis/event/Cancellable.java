package civitas.celestis.event;

/**
 * <h2>Cancellable</h2>
 * <p>
 * Cancellable events can be declared as cancelled by a lower priority listener.
 * Higher priority listeners should respect this declaration and not process the event.
 * </p>
 */
public interface Cancellable extends Event {
    /**
     * Checks if this event has been cancelled.
     *
     * @return {@code true} if this event was cancelled
     */
    boolean isCancelled();

    /**
     * Sets whether this event has been cancelled.
     *
     * @param cancelled {@code true} to cancel this event
     */
    void setCancelled(boolean cancelled);
}
