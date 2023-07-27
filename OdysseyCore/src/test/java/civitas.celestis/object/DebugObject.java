package civitas.celestis.object;

import civitas.celestis.geometry.profile.Geometry;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

public class DebugObject extends AbstractPhysicalObject {
    public DebugObject(@Nonnull UUID uniqueId, @Nonnull Vector location, @Nonnull Quaternion rotation, @Nonnegative double mass, @Nonnull Geometry geometry, @Nonnull Vector acceleration, @Nonnull Quaternion rotationRate) {
        super(uniqueId, location, rotation, mass, geometry, acceleration, rotationRate);
    }
}
