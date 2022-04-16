package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class GoBikingEvent extends Event {
    private final SportItem sportItem;

    public GoBikingEvent(Person person, SportItem sportItem) {
        super(null, person, Constant.EVENT_GO_BIKING_TIME, EventType.ENUM_GO_BIKING_EVENT);
        this.sportItem = sportItem;
    }

    public SportItem getSportItem() {
        return sportItem;
    }
}
