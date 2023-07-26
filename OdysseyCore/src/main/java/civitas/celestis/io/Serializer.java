package civitas.celestis.io;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>Serializer</h2>
 * <p>A serializer class for serializable objects.</p>
 *
 * @param <T> Type of object to serialize
 */
public interface Serializer<T extends Serializable> {
    /**
     * Serializes given object to a string.
     *
     * @param object Object to serialize
     * @return Serialized string
     */
    @Nonnull
    String serialize(@Nonnull T object);
}
