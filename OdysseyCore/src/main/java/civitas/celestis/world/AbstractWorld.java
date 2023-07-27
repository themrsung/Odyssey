package civitas.celestis.world;

import civitas.celestis.Odyssey;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h2>AbstractWorld</h2>
 * <p>A basic world.</p>
 */
public abstract class AbstractWorld implements World {
    /**
     * Creates a new abstract world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name Name of this world
     * @param objects List of objects in this world
     * @param overlaps List of overlapping objects in this world
     */
    public AbstractWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull List<Pair<PhysicalObject>> overlaps
    ) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = objects;
        this.overlaps = overlaps;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    protected final List<BaseObject> objects;
    @Nonnull
    protected final List<Pair<PhysicalObject>> overlaps;

    @Override
    public void tick(@Nonnull Duration delta) {
        // Get all possible pairs
        final List<Pair<PhysicalObject>> pairs = Pair.of(objects.stream()
                .filter(PhysicalObject.class::isInstance)
                .map(PhysicalObject.class::cast).toList());

        // Remove invalid cache
        overlaps.removeIf(p -> !pairs.contains(p));

        // Check for overlaps
        pairs.forEach(pair -> {
            final PhysicalObject o1 = pair.first();
            final PhysicalObject o2 = pair.second();

            if (o1.overlaps(o2)) {
                if (!overlaps.contains(pair)) {
                    overlaps.add(pair);

                    // Fire collision event
                    Odyssey.getEventManager().call(new ObjectsCollidedEvent(pair));
                }
            } else {
                overlaps.remove(pair);
            }
        });

        // Tick objects
        objects.forEach(o -> o.tick(delta));
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
    public BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException {
        for (BaseObject object : objects) {
            if (object.getUniqueId().equals(uniqueId)) return object;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
    }

    @Override
    @Nonnull
    public List<Pair<PhysicalObject>> getOverlaps() {
        return List.copyOf(overlaps);
    }

    @Override
    public void addObject(@Nonnull BaseObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull BaseObject object) {
        objects.remove(object);
        if (object instanceof PhysicalObject physical) {
            overlaps.removeIf(p -> p.contains(physical));
        }
    }
}
