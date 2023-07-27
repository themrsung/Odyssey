package civitas.celestis.util;

import javax.annotation.Nonnegative;

/**
 * <h2>Counter</h2>
 * <p>Provides an easy way of iterating through a given range.</p>
 */
public final class Counter {
    /**
     * Creates a new counter with zero as its lower bound, and given upper bound.
     * Iterator starts at zero.
     *
     * @param upperBound Upper bound of counter
     */
    public Counter(@Nonnegative int upperBound) {
        this(0, upperBound, 0);
    }

    /**
     * Creates a new counter with given bounds. Iterator starts at lower bound.
     *
     * @param lowerBound Lower bound of counter
     * @param upperBound Upper bound of counter
     * @throws IllegalArgumentException When the lower bound is equal to or greater than the upper bound
     */
    public Counter(int lowerBound, int upperBound) throws IllegalArgumentException {
        this(lowerBound, upperBound, lowerBound);
    }

    /**
     * All-args constructor.
     *
     * @param lowerBound Lower bound of counter
     * @param upperBound Upper bound of counter
     * @param i          Iterator of counter
     * @throws IllegalArgumentException When {@code i >= upperBound} or {@code i < lowerbound}
     */
    public Counter(int lowerBound, int upperBound, int i) throws IllegalArgumentException {
        if (i >= upperBound || i < lowerBound) {
            throw new IllegalArgumentException("Counter cannot be build when starting iterator is not within bounds");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.i = i;
    }

    //
    // Values
    //

    private final int lowerBound;
    private final int upperBound;
    private int i;

    //
    // Methods
    //

    /**
     * Gets the lower bound of this counter.
     *
     * @return Lower bound
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Gets the upper bound of this counter.
     *
     * @return Upper bound
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * <b>Gets</b> the current iterator. <b>This does NOT increment the counter.</b>
     * To return and increment the counter, use {@link Counter#get()}.
     *
     * @return Current iterator
     */
    public int getCurrentIterator() {
        return i;
    }

    /**
     * Gets the current iterator, <b>then increments it.</b>
     *
     * @return Current iterator
     */
    public int get() {
        final int iterator = this.i; // Backup value to return
        increment();
        return iterator;
    }

    /**
     * Increments the counter, <b>then returns it.</b>
     *
     * @return Incremented counter (current + 1)
     */
    public int next() {
        return increment();
    }

    /**
     * Increments the counter.
     *
     * @return Incremented value
     */
    private int increment() {
        if (i == upperBound - 1) {
            i = lowerBound;
            return lowerBound;
        } else {
            return i++;
        }
    }
}
