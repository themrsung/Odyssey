package civitas.celestis;

import civitas.celestis.geometry.profile.SphericalGeometry;
import civitas.celestis.gui.component.viewport.Viewport;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.RealisticObject;
import civitas.celestis.task.Task;
import civitas.celestis.util.RotationBuilder;
import civitas.celestis.world.RealisticWorld;
import civitas.celestis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

/**
 * Test class for OdysseyCore.
 */
public final class OdysseyTest {
    public static void main(String[] args) {
        Odyssey.start();

        final World world = new RealisticWorld(
                UUID.randomUUID(),
                "Test World",
                new Vector3(0, -9.807, 0),
                1.225
        );

        Odyssey.getWorldManager().addWorld(world);

        final BaseObject o1 = new RealisticObject(
                UUID.randomUUID(),
                new Vector3(0, 0, 100),
                new SphericalGeometry(10),
                1
        );

        o1.setRotationRate(RotationBuilder.fromAxisAngle(new Vector3(12, 22, 13), Math.toRadians(90)).build());

        world.addObject(o1);

        final JFrame frame = new JFrame("Test");
        final Viewport viewport = new Viewport(world);

        frame.setSize(1920, 1080);
        frame.setVisible(true);

        frame.add(viewport);

        viewport.setInflation(100);


        // Let's have some fun
        Odyssey.getScheduler().registerTask(delta -> o1.rotateRate(new Quaternion(
                Math.random() * 132,
                Math.random() * 932039,
                Math.random() * 282,
                Math.random() * 131
        ).normalize().scale(100)));


        Odyssey.getScheduler().registerTask(delta -> viewport.renderAndRepaint());

        Odyssey.getScheduler().registerTask(new Task() {
            @Override
            public void execute(@Nonnull Duration delta) {
                System.out.println(o1.getLocation());
            }

            @Nonnull
            @Override
            public Duration getInterval() {
                return Duration.standardSeconds(1);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                Odyssey.stop();
            }
        });
    }
}
