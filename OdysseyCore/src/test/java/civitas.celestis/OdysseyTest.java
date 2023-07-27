package civitas.celestis;

import civitas.celestis.geometry.profile.SphericalGeometry;
import civitas.celestis.number.Numbers;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.DebugObject;
import civitas.celestis.task.Task;
import civitas.celestis.world.DebugWorld;
import civitas.celestis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Test class for OdysseyCore.
 */
public final class OdysseyTest {
    public static void main(String[] args) {
        Odyssey.start();

        final World world = new DebugWorld(UUID.randomUUID(), "World", new ArrayList<>(), new ArrayList<>(), new Vector(0, -9.807, 0), 1.293);
        final BaseObject object = new DebugObject(
                UUID.randomUUID(),
                new Vector(0, 555, 100),
                Quaternion.IDENTITY,
                100,
                new SphericalGeometry(0.5),
                new Vector(0, 0, -50),
                Quaternion.IDENTITY
        );

        final BaseObject o2 = new DebugObject(
                UUID.randomUUID(),
                new Vector(0, 555, -100),
                Quaternion.IDENTITY,
                100,
                new SphericalGeometry(0.5),
                new Vector(0, 0, 50),
                Quaternion.IDENTITY
        );

        world.addObject(object);
        world.addObject(o2);
        Odyssey.getWorldManager().addWorld(world);

        Odyssey.getScheduler().registerTask(new Task() {
            @Override
            public void execute(@Nonnull Duration delta) {
                System.out.println(object.getLocation());
                System.out.println(o2.getLocation());
            }

            @Nonnull
            @Override
            public Duration getInterval() {
                return new Duration(1000);
            }
        });

        final Vector v = new Vector(1002, 2930, 203).normalize();
        System.out.println(v + " " + v.magnitude());
        System.out.println(Numbers.isqrt(4));
    }
}
