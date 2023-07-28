package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * <h2>AbstractWorld</h2>
 * <p>The default implementation of {@link World}.</p>
 */
public abstract class AbstractWorld implements World {
    /**
     * Creates a new world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name     Name of this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull String name) {
        this(uniqueId, name, new ArrayList<>());
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name     Name of this world
     * @param objects  List of objects in this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull List<BaseObject> objects) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = objects;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    private final List<BaseObject> objects;

    @Override
    public void tick(@Nonnull Duration delta) {
        getObjects().forEach(o -> o.tick(delta));
    }

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public List<BaseObject> getObjects() {
        return new ArrayList<>(objects);
    }

    @Nonnull
    @Override
    public <O extends BaseObject> Stream<O> getObjects(@Nonnull Class<O> objectClass) {
        return List.copyOf(objects).stream().filter(objectClass::isInstance).map(objectClass::cast);
    }

    @Nonnull
    @Override
    public BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException {
        for (BaseObject object : objects) {
            if (object.getUniqueId().equals(uniqueId)) return object;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
    }

    @Nonnull
    @Override
    public <O extends BaseObject> O getObject(@Nonnull UUID uniqueId, @Nonnull Class<O> type) throws NullPointerException {
        final AtomicReference<O> object = new AtomicReference<>(null);

        getObjects(type).forEach(o -> {
            if (o.getUniqueId().equals(uniqueId)) object.set(o);
        });

        if (object.get() == null) {
            throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
        }

        return object.get();
    }

    @Override
    public <O extends BaseObject> void addObject(@Nonnull O object) {
        objects.add(object);
    }

    @Override
    public <O extends BaseObject> void removeObject(@Nonnull O object) {
        objects.remove(object);
    }
}
