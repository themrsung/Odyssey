package civitas.celestis.io;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Serializable;

/**
 * <h2>Deserializer</h2>
 * <p>A deserializer class for serializable objects.</p>
 *
 * @param <T> Type of object to deserialize
 */
public interface Deserializer<T extends Serializable> {
    /**
     * Deserializes an input string to an object.
     *
     * @param input Input string
     * @return Deserialized object
     * @throws IOException When an exception occurs during deserialization
     */
    @Nonnull
    T deserialize(@Nonnull String input) throws IOException;
}
