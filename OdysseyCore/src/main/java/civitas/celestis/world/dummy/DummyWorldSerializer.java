package civitas.celestis.world.dummy;

import civitas.celestis.io.Serializer;

import javax.annotation.Nonnull;

/**
 * <h2>DummyWorldSerializer</h2>
 * <p>A serializer for dummy worlds.</p>
 */
public class DummyWorldSerializer implements Serializer<DummyWorld> {
    @Nonnull
    @Override
    public String serialize(@Nonnull DummyWorld object) {
        return "DummyWorld{uniqueId=" + object.getUniqueId() + ", name=" + object.getName() + "}";
    }
}
