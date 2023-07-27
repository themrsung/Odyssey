package civitas.celestis.listener;

import civitas.celestis.event.DummyEvent;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;

import javax.annotation.Nonnull;

public class DummyListener implements Listener {
    @EventHandler
    public void onDummyEvent(@Nonnull DummyEvent event) {
        System.out.println("Dummy event triggered!");
    }
}
