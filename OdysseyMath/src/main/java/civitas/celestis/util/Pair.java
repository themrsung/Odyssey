package civitas.celestis.util;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Pair</h2>
 * <p>
 * A pair of two objects.
 * Two pairs with the same composition will be considered equal regardless of their order.
 * </p>
 *
 * @param first  First element
 * @param second Second element
 * @param <T>    Type of element
 */
public record Pair<T>(
        @Nonnull T first,
        @Nonnull T second
) implements Iterable<T> {
    /**
     * Given a list of elements, this returns all possible permutations of pairs.
     * This assumes that the given list does not contain duplicate elements.
     * A pair with itself will not be counted.
     *
     * @param list List to get pairs of
     * @param <U>  Type of object
     * @return List of all possible pairs
     */
    @Nonnull
    public static <U> List<Pair<U>> of(@Nonnull List<U> list) {
        final List<Pair<U>> pairs = new ArrayList<>();

        list.forEach(o1 -> list.stream().filter(o -> !o.equals(o1)).forEach(o2 -> {
            final Pair<U> p = new Pair<>(o1, o2);
            if (pairs.contains(p)) return;

            pairs.add(p);
        }));

        return pairs;
    }

    /**
     * Checks if this pair contains given object.
     *
     * @param object Object to check
     * @return {@code true} if this pair contains given object
     */
    public boolean contains(@Nonnull T object) {
        return first.equals(object) || second.equals(object);
    }

    /**
     * Given one object, this returns the other object.
     *
     * @param object Object to check
     * @return The other object
     * @throws IllegalArgumentException When the object is not a member of {@code this}
     */
    @Nonnull
    public T other(@Nonnull T object) throws IllegalArgumentException {
        if (first.equals(object)) return second;
        if (second.equals(object)) return first;

        throw new IllegalArgumentException("Object is not a member of this pair.");
    }

    /**
     * Checks for equality without regard to the order of components.
     *
     * @param obj Object to compare to
     * @return {@code true} if the composition is the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Pair<?> p)) return false;
        return (Objects.equals(first, p.first) && Objects.equals(second, p.second)) ||
                (Objects.equals(second, p.first) && Objects.equals(first, p.second));
    }

    /**
     * Gets the iterator of values of this pair.
     *
     * @return Iterator of values
     */
    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(first, second).iterator();
    }
}
