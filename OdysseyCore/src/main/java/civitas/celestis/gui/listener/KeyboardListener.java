package civitas.celestis.gui.listener;

import javax.annotation.Nonnull;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>KeyboardListener</h2>
 * <p>A listener which listens to key input.</p>
 */
public abstract class KeyboardListener extends KeyAdapter {
    protected final Map<Integer, Boolean> pressedKeys = new HashMap<>();

    /**
     * Gets a map of currently pressed keys.
     * @return Map of pressed keys
     */
    @Nonnull
    public Map<Integer, Boolean> getPressedKeys() {
        return Map.copyOf(pressedKeys);
    }

    @Override
    public void keyPressed(@Nonnull KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(@Nonnull KeyEvent e) {
        pressedKeys.put(e.getKeyCode(), false);
    }
}
