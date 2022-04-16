package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.building.Window;
import cz.cvut.fel.omo.smarthome.enums.EventType;

public class CloseBlindsEvent extends Event {
    private final Window window;

    public CloseBlindsEvent(Window window, Person person) {
        super(null, person, 0, EventType.ENUM_CLOSE_BLINDS_EVENT);
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }
}
