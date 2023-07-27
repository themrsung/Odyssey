package civitas.celestis.graphics;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.awt.*;

/**
 * <h2>Colors</h2>
 * <p>Contains color utility functions.</p>
 */
public final class Colors {
    /**
     * Brightens a color proportionally to given intensity.
     *
     * @param in        Input color
     * @param intensity Intensity of light
     * @return Brightened color
     */
    @Nonnull
    public static Color brighten(@Nonnull Color in, @Nonnegative double intensity) {
        double red = in.getRed();
        double green = in.getGreen();
        double blue = in.getBlue();
        int alpha = in.getAlpha();

        red += intensity;
        green += intensity;
        blue += intensity;

        red = Math.min(red, 255);
        green = Math.min(green, 255);
        blue = Math.min(blue, 255);

        return new Color((int) red, (int) green, (int) blue, alpha);
    }
}
