package civitas.celestis.world.dummy;

import civitas.celestis.io.Deserializer;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h2>DummyWorldDeserializer</h2>
 * <p>A deserializer for dummy worlds.</p>
 */
public class DummyWorldDeserializer implements Deserializer<DummyWorld> {
    @Nonnull
    @Override
    public DummyWorld deserialize(@Nonnull String input) throws IOException {
        if (!input.startsWith("DummyWorld{")) throw new IOException("Given input string is not a dummy world.");

        final String[] strings = input
                .replaceAll("DummyWorld\\{", "")
                .replaceAll("}", "")
                .split(", ");

        final AtomicReference<UUID> uniqueId = new AtomicReference<>(null);
        final AtomicReference<String> name = new AtomicReference<>(null);

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) return;

            switch (split[0]) {
                case "uniqueId" -> uniqueId.set(UUID.fromString(split[1]));
                case "name" -> name.set(split[1]);
            }
        });

        if (uniqueId.get() == null || name.get() == null) throw new IOException("Given input string is invalid.");

        return new DummyWorld(uniqueId.get(), name.get());
    }
}
