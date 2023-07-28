package civitas.celestis;

/**
 * Test class for OdysseyCore.
 */
public final class OdysseyTest {
    public static void main(String[] args) {
        Odyssey.start();

//        final World world = new DebugWorld(UUID.randomUUID(), "World", new ArrayList<>(), new ArrayList<>(), new Vector(0, -9.807, 0), 1.293);
//        final BaseObject object = new DebugObject(
//                UUID.randomUUID(),
//                new Vector(50, 555, 0),
//                Quaternion.IDENTITY,
//                100000000,
//                new SphericalGeometry(10),
//                RotationBuilder.fromAxisAngle(Vector.NEGATIVE_Z, Math.toRadians(181)).build()
//        );
//
//        final BaseObject o2 = new DebugObject(
//                UUID.randomUUID(),
//                new Vector(-500, 555, 0),
//                Quaternion.IDENTITY,
//                10000000000d,
//                new SphericalGeometry(2.5),
//                new Vector(1000, 0, 0),
//                RotationBuilder.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(23)).build()
//        );
//
//        final BaseObject o3 = new DebugObject(
//                UUID.randomUUID(),
//                new Vector(0, 555, 100),
//                Quaternion.IDENTITY,
//                100,
//                new SphericalGeometry(49),
//                new Vector(0, 1000, 0),
//                RotationBuilder.fromAxisAngle(new Vector(2, -3, 29), Math.toRadians(500)).build()
//        );
//
//        world.addObject(object);
//        world.addObject(o2);
//        world.addObject(o3);
//
//        Odyssey.getWorldManager().addWorld(world);
//
//        Odyssey.getScheduler().registerTask(new Task() {
//            @Override
//            public void execute(@Nonnull Duration delta) {
//                System.out.println(o3.getRotation());
//            }
//
//            @Nonnull
//            @Override
//            public Duration getInterval() {
//                return new Duration(1000);
//            }
//        });
//
//        final JFrame frame = new JFrame("Test");
//        final Viewport viewport = new Viewport();
//
//        viewport.setWorld(world);
//        viewport.setOrigin(new Vector(0, 555, -100));
//        viewport.setRotation(Quaternion.IDENTITY);
//
//        frame.add(viewport);
//
//        frame.setSize(1920, 1080);
//        frame.setVisible(true);
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                frame.dispose();
//                Odyssey.stop();
//            }
//        });
//
//        Odyssey.getScheduler().registerTask(delta -> viewport.render());
    }
}
