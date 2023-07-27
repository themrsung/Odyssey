package civitas.celestis;

import civitas.celestis.geometry.profile.SphericalGeometry;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.DebugObject;
import civitas.celestis.task.Task;
import civitas.celestis.world.DebugWorld;
import civitas.celestis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
                new Vector(50, 555, 0),
                Quaternion.IDENTITY,
                100000000,
                new SphericalGeometry(10),
                new Vector(-50, 0, 0),
                Quaternion.fromAxisAngle(Vector.NEGATIVE_Z, Math.toRadians(181))
        );

        final BaseObject o2 = new DebugObject(
                UUID.randomUUID(),
                new Vector(-500, 555, 0),
                Quaternion.IDENTITY,
                10000000000d,
                new SphericalGeometry(2.5),
                new Vector(1000, 0, 0),
                Quaternion.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(23))
        );

        final BaseObject o3 = new DebugObject(
                UUID.randomUUID(),
                new Vector(0, 555, 100),
                Quaternion.IDENTITY,
                100,
                new SphericalGeometry(49),
                new Vector(0, 1000, 0),
                Quaternion.fromAxisAngle(new Vector(2, -3, 29), Math.toRadians(500))
        );

        world.addObject(object);
        world.addObject(o2);
        world.addObject(o3);

        Odyssey.getWorldManager().addWorld(world);

        Odyssey.getScheduler().registerTask(new Task() {
            @Override
            public void execute(@Nonnull Duration delta) {
                System.out.println(o3.getRotation());
            }

            @Nonnull
            @Override
            public Duration getInterval() {
                return new Duration(1000);
            }
        });

        final JFrame frame = new JFrame("Test");
        final Viewport viewport = new Viewport(world, new Vector(0, 555, -50), Quaternion.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(1)));
        frame.add(viewport);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                Odyssey.stop();
            }
        });

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        viewport.setSize(frame.getSize());

        Odyssey.getScheduler().registerTask(delta -> frame.repaint());

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        viewport.setRotation(Quaternion.fromAxisAngle(Vector.POSITIVE_X, Math.toRadians(-1)).multiply(viewport.getRotation()));
                    }
                    case KeyEvent.VK_DOWN -> {
                        viewport.setRotation(Quaternion.fromAxisAngle(Vector.POSITIVE_X, Math.toRadians(1)).multiply(viewport.getRotation()));
                    }
                    case KeyEvent.VK_RIGHT -> {
                        viewport.setRotation(Quaternion.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(1)).multiply(viewport.getRotation()));
                    }
                    case KeyEvent.VK_LEFT -> {
                        viewport.setRotation(Quaternion.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(-1)).multiply(viewport.getRotation()));
                    }
                }
            }
        });
    }
}
