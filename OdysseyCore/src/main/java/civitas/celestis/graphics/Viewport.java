package civitas.celestis.graphics;

import civitas.celestis.geometry.ColoredVertex;
import civitas.celestis.geometry.LightRay;
import civitas.celestis.geometry.solid.Solid;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.number.Vectors;
import civitas.celestis.object.PhysicalObject;
import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

/**
 * <h2>Viewport</h2>
 * <p>Renders a scene on-screen.</p>
 */
public class Viewport extends JPanel {
    //
    // Constructors
    //

    /**
     * Creates a new viewport with a predefined scene.
     *
     * @param layout Layout manager object
     * @param scene  Scene to use
     * @param config Configuration of this viewport
     */
    public Viewport(@Nonnull LayoutManager layout, @Nonnull Scene scene, @Nonnull ViewportConfiguration config) {
        super(layout, true);
        this.scene = scene;
        this.origin = Vector.ZERO;
        this.rotation = Quaternion.IDENTITY;
        this.configuration = config;
    }

    /**
     * Creates a new viewport with a predefined scene.
     *
     * @param layout Layout manager object
     * @param scene  Scene to use
     */
    public Viewport(@Nonnull LayoutManager layout, @Nonnull Scene scene) {
        this(layout, scene, ViewportConfiguration.getDefaultConfiguration());
    }

    /**
     * Creates a new viewport with a custom layout.
     *
     * @param layout Layout manager object
     */
    public Viewport(@Nonnull LayoutManager layout) {
        this(layout, new Scene(), ViewportConfiguration.getDefaultConfiguration());
    }

    /**
     * Creates a new viewport with a predefined scene.
     *
     * @param scene  Scene to use
     * @param config Configuration of this viewport
     */
    public Viewport(@Nonnull Scene scene, @Nonnull ViewportConfiguration config) {
        super(true);
        this.scene = scene;
        this.origin = Vector.ZERO;
        this.rotation = Quaternion.IDENTITY;
        this.configuration = config;
    }

    /**
     * Creates a new viewport with a predefined scene.
     *
     * @param scene Scene to use
     */
    public Viewport(@Nonnull Scene scene) {
        this(scene, ViewportConfiguration.getDefaultConfiguration());
    }

    /**
     * Creates a new viewport.
     */
    public Viewport() {
        this(new Scene(), ViewportConfiguration.getDefaultConfiguration());
    }

    //
    // Variables
    //

    @Nonnull
    private final Scene scene;
    @Nonnull
    private ViewportConfiguration configuration;
    @Nullable
    private World world;
    @Nonnull
    private Vector origin;
    @Nonnull
    private Quaternion rotation;

    private boolean rendering = false;

    //
    // Methods
    //

    /**
     * Paints the scene on-screen.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(@Nonnull Graphics g) {
        // Clear the screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Translate to center
        g.translate(getWidth() / 2, getHeight() / 2);

        // Get vertices from scene
        scene.getTransformedVertices(origin, rotation)
                .sorted((v1, v2) -> Double.compare(v2.centroid().magnitude2(), v1.centroid().magnitude2()))
                .forEach(v -> {
                    // Don't render vertices behind camera
                    for (Vector vector : v) {
                        if (vector.z() <= 0) return;
                    }

                    // Define polygon
                    final PolygonX polygon = new PolygonX();

                    // Add points
                    v.forEach(p -> polygon.addPoint(Vectors.translate(
                            p.multiply(configuration.inflation()),
                            configuration.focalLength())
                    ));

                    // Draw polygon
                    g.setColor((v instanceof ColoredVertex cv) ? cv.color() : Color.BLUE);
                    g.fillPolygon(polygon);
                });

        // Mark state as not rendering
        rendering = false;
    }

    /**
     * Renders the scene, then calls {@link Viewport#repaint()}.
     */
    public void render() {
        // Prevent duplicate rendering
        if (rendering) return;

        // Mark state as rendering
        rendering = true;

        // Cannot render when world points to null
        if (world == null) return;

        // Wipe scene
        scene.clear();

        // Add vertices
        world.getObjects().stream()
                .filter(PhysicalObject.class::isInstance)
                .map(PhysicalObject.class::cast)
                .forEach(o -> {
                    final Solid s = o.getSolid();
                    s.vertices().forEach(scene::addVertex);
                });

        // Shoot light rays
        final LightRay ray = new LightRay(Vector.ZERO, Vector.POSITIVE_Z, 100);
        scene.shootLightRay(ray, 10);

        // Call repaint()
        repaint();
    }

    //
    // Accessors
    //

    /**
     * Gets the scene of this viewport.
     *
     * @return Scene
     */
    @Nonnull
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the configuration of this viewport.
     *
     * @return Viewport configuration
     */
    @Nonnull
    public ViewportConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Gets the world of this viewport.
     *
     * @return Viewport
     */
    @Nullable
    public World getWorld() {
        return world;
    }

    /**
     * Gets the origin of this viewport.
     *
     * @return Origin
     */
    @Nonnull
    public Vector getOrigin() {
        return origin;
    }

    /**
     * Gets the rotation of this viewport.
     *
     * @return Rotation
     */
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * Sets the configuration of this viewport.
     *
     * @param configuration Viewport configuration
     */
    public void setConfiguration(@Nonnull ViewportConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Sets the world of this viewport.
     *
     * @param world World
     */
    public void setWorld(@Nullable World world) {
        this.world = world;
    }

    /**
     * Sets the origin of this viewport.
     *
     * @param origin Origin
     */
    public void setOrigin(@Nonnull Vector origin) {
        this.origin = origin;
    }

    /**
     * Sets the rotation of this viewport.
     *
     * @param rotation Rotation
     */
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }
}
