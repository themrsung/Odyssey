package civitas.celestis.gui.component.viewport;

import civitas.celestis.geometry.ray.LightRay;
import civitas.celestis.geometry.ray.Ray;
import civitas.celestis.graphics.PolygonX;
import civitas.celestis.graphics.Scene;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector3;
import civitas.celestis.number.Vectors;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.TangibleObject;
import civitas.celestis.world.World;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Viewport</h2>
 * <p>A viewport handles the rendering of a world to a scene.</p>
 */
public class Viewport extends JPanel {
    /**
     * Creates a new viewport.
     *
     * @param layout Layout manager object
     * @param world  World to render
     */
    public Viewport(@Nonnull LayoutManager layout, @Nonnull World world) {
        super(layout, true);
        this.world = world;

        this.scene = new Scene();
        this.hiddenObjects = new ArrayList<>();

        initialize();
    }

    /**
     * Creates a new viewport.
     *
     * @param world World to render
     */
    public Viewport(@Nonnull World world) {
        super(true);
        this.world = world;

        this.scene = new Scene();
        this.hiddenObjects = new ArrayList<>();

        initialize();
    }

    /**
     * Initializes default settings.
     */
    private void initialize() {
        this.focalLength = 500;
        this.origin = Vector3.ZERO;
        this.rotation = Quaternion.IDENTITY;
        this.inflation = 10;
    }

    //
    // Methods
    //

    /**
     * Renders and repaints this viewport.
     */
    public void renderAndRepaint() {
        render();
        repaint();
    }

    /**
     * Renders this viewport. This does not repaint the panel.
     */
    public void render() {
        // Prevent modification of scene while painting
        if (painting) return;

        // Clear scene
        scene.clear();

        // Add object vertices
        world.getObjects(TangibleObject.class).forEach(o -> scene.addVertices(o.getSolid().vertices()));

        // Handle lighting
        final Ray test = new LightRay(Vector3.ZERO, Vector3.POSITIVE_Z, 10);
        for (int i = 0; i < 10; i++) {
            scene.shootRay(test, 1);
        }
    }

    /**
     * Paints the scene on-screen.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(@Nonnull Graphics g) {
        // Prevent unnecessary computation
        if (painting) return;

        // Mark state as painting
        painting = true;

        // Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Move origin to center
        g.translate(getWidth() / 2, getHeight() / 2);

        // Draw vertices
        scene.getVertices(origin, rotation, inflation)
                // Filter out vertices behind viewport
                .filter(v -> v.centroid().z() > 0)

                // Sort by distance from origin descending
                .sorted((v1, v2) -> Double.compare(v2.centroid().magnitude2(), v1.centroid().magnitude2()))

                // Loop through sorted vertices
                .forEach(v -> {
                    // Create polygon object
                    final PolygonX polygon = new PolygonX();
                    v.forEach(p -> polygon.addPoint(Vectors.translate(p, focalLength)));

                    // Draw polygon on-screen
                    g.setColor(v.color());
                    g.fillPolygon(polygon);

                    ////////////////////////////
                    /////////// TEMP ///////////
                    ////////////////////////////

                    g.setColor(Color.BLACK);
                    g.drawPolygon(polygon);
                });


        // Mark state as not painting
        painting = false;
    }

    //
    // Context
    //
    @Nonnull
    protected final World world;
    @Nonnull
    protected final Scene scene;
    @Nonnull
    protected final List<BaseObject> hiddenObjects;
    @Nonnull
    protected Vector3 origin;
    @Nonnull
    protected Quaternion rotation;

    //
    // Settings
    //
    @Nonnegative
    protected double focalLength;
    @Nonnegative
    protected double inflation;

    //
    // Markers
    //
    protected boolean painting = false;

    //
    // Accessors
    //

    /**
     * Gets the world this viewport is rendering.
     *
     * @return World of viewport
     */
    @Nonnull
    public World getWorld() {
        return world;
    }

    /**
     * Gets the scene object of this viewport.
     *
     * @return Scene object
     */
    @Nonnull
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets a list of hidden objects of this viewport.
     *
     * @return List of hidden objects
     */
    @Nonnull
    public List<BaseObject> getHiddenObjects() {
        return List.copyOf(hiddenObjects);
    }

    /**
     * Gets the origin of this viewport.
     *
     * @return Viewport origin
     */
    @Nonnull
    public Vector3 getOrigin() {
        return origin;
    }

    /**
     * Gets the rotation of this viewport.
     *
     * @return Viewport rotation
     */
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * Gets the current focal length of this viewport.
     *
     * @return Focal length
     */
    @Nonnegative
    public double getFocalLength() {
        return focalLength;
    }

    /**
     * Gets the current inflation of this viewport.
     *
     * @return Inflation
     */
    @Nonnegative
    public double getInflation() {
        return inflation;
    }

    /**
     * Adds an object to the hidden objects list.
     *
     * @param object Object to hide
     */
    public void hideObject(@Nonnull BaseObject object) {
        hiddenObjects.add(object);
    }

    /**
     * Removes an object from the hidden objects list.
     *
     * @param object Object to shoe
     */
    public void showObject(@Nonnull BaseObject object) {
        hiddenObjects.remove(object);
    }

    /**
     * Clears the hidden objects list.
     */
    public void clearHiddenObjects() {
        hiddenObjects.clear();
    }

    /**
     * Gets the origin of this viewport.
     *
     * @param origin Viewport origin
     */
    public void setOrigin(@Nonnull Vector3 origin) {
        this.origin = origin;
    }

    /**
     * Gets the viewing rotation of this viewport.
     *
     * @param rotation Viewport rotation
     */
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    /**
     * Sets the focal length of this viewport.
     *
     * @param focalLength Focal length
     */
    public void setFocalLength(@Nonnegative double focalLength) {
        this.focalLength = focalLength;
    }

    /**
     * Sets the inflation of this viewport.
     *
     * @param inflation Inflation
     */
    public void setInflation(@Nonnegative double inflation) {
        this.inflation = inflation;
    }
}
