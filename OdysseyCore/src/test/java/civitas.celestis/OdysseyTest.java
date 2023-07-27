package civitas.celestis;

import civitas.celestis.event.DummyEvent;
import civitas.celestis.listener.DummyListener;

/**
 * Test class for OdysseyCore.
 */
public final class OdysseyTest {
    public static void main(String[] args) {
        Odyssey.start();
        Odyssey.getEventManager().registerListener(new DummyListener());
        Odyssey.getEventManager().call(new DummyEvent());
    }
}
