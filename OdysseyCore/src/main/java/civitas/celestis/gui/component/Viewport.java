package civitas.celestis.gui.component;

import civitas.celestis.graphics.PolygonX;
import civitas.celestis.graphics.Scene;
import civitas.celestis.number.Vectors;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * <h2>Viewport</h2>
 * <p>A viewport renders a scene on a panel.</p>
 */
public class Viewport extends JPanel {
    /**
     * Creates a new viewport.
     *
     * @param layout Layout manager object
     * @param scene  Scene to render
     */
    public Viewport(@Nonnull LayoutManager layout, @Nonnull Scene scene) {
        super(layout, true);
        this.scene = scene;
        this.context = ViewportContext.getEmptyContext();
        this.configuration = ViewportConfiguration.getDefaultConfiguration();
    }

    /**
     * Creates a new viewport.
     *
     * @param layout Layout manager object
     */
    public Viewport(@Nonnull LayoutManager layout) {
        this(layout, new Scene());
    }

    /**
     * Creates a new viewport.
     *
     * @param scene Scene to render
     */
    public Viewport(@Nonnull Scene scene) {
        super(true);
        this.scene = scene;
        this.context = ViewportContext.getEmptyContext();
        this.configuration = ViewportConfiguration.getDefaultConfiguration();
    }

    /**
     * Creates a new viewport.
     */
    public Viewport() {
        this(new Scene());
    }

    /**
     * Renders the scene, then calls {@link Viewport#repaint()}.
     */
    public void renderAndRepaint() {
        render();
        repaint();
    }

    /**
     * Renders the scene. <b>Does not call {@link Viewport#repaint()}.</b>
     */
    public void render() {

    }

    /**
     * Paints the rendered scene on-screen.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(@Nonnull Graphics g) {
        // Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Translate coordinates
        g.translate(getWidth() / 2, getHeight() / 2);

        // Draw vertices
        scene.getVertices(context.origin(), context.rotation(), configuration.inflation())
                .sorted((v1, v2) -> Double.compare(v2.centroid().magnitude2(), v1.centroid().magnitude2()))
                .forEach(v -> {
                    final PolygonX polygon = new PolygonX();
                    v.forEach(p -> polygon.addPoint(Vectors.translate(p, configuration.focalLength())));

                    g.setColor(v.color());
                    g.fillPolygon(polygon);
                });
    }

    @Nonnull
    private final Scene scene;
    @Nonnull
    private ViewportContext context;
    @Nonnull
    private ViewportConfiguration configuration;

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
     * Gets the context of this viewport.
     *
     * @return Viewport context
     */
    @Nonnull
    public ViewportContext getContext() {
        return context;
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
     * Sets the context of this viewport.
     *
     * @param context Viewport context
     */
    public void setContext(@Nonnull ViewportContext context) {
        this.context = context;
    }

    /**
     * Sets the configuration of this viewport.
     *
     * @param configuration Viewport configuration
     */
    public void setConfiguration(@Nonnull ViewportConfiguration configuration) {
        this.configuration = configuration;
    }
}
