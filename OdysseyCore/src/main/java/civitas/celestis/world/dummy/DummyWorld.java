package civitas.celestis.world.dummy;

import civitas.celestis.io.Deserializer;
import civitas.celestis.io.Serializer;
import civitas.celestis.number.Vector;
import civitas.celestis.world.AbstractWorld;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.UUID;

/**
 * <h2>DummyWorld</h2>
 * <p>
 *     A world which is not intended to be saved or loaded.
 *     This is useful for lobbies or start screens.
 * </p>
 */
public class DummyWorld extends AbstractWorld {
    /**
     * Creates a new dummy world.
     * @param uniqueId Unique identifier of this world
     * @param name Name of this world
     */
    public DummyWorld(@Nonnull UUID uniqueId, @Nonnull String name) {
        super(uniqueId, name, new ArrayList<>(), new ArrayList<>(), Vector.ZERO, 0);
    }

    @Nonnull
    @Override
    public Deserializer<DummyWorld> deserializer() {
        return new DummyWorldDeserializer();
    }

    @Nonnull
    @Override
    public Serializer<DummyWorld> serializer() {
        return new DummyWorldSerializer();
    }
}
