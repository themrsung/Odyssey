package civitas.celestis.world;

import civitas.celestis.number.Vector;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.util.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class DebugWorld extends AbstractRealisticWorld {
    public DebugWorld(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull List<BaseObject> objects, @Nonnull List<Pair<PhysicalObject>> overlaps, @Nonnull Vector gravity, @Nonnegative double airDensity) {
        super(uniqueId, name, objects, overlaps, gravity, airDensity);
    }
}
