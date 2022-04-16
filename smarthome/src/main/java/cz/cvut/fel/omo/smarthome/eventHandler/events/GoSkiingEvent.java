package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class GoSkiingEvent extends Event {
    private final SportItem sportItem;

    public GoSkiingEvent(Person person, SportItem sportItem) {
        super(null, person, Constant.EVENT_GO_SKIING_EVENT_TIME, EventType.ENUM_GO_SKIING_EVENT);
        this.sportItem = sportItem;
    }

    public SportItem getSportItem() {
        return sportItem;
    }
}
