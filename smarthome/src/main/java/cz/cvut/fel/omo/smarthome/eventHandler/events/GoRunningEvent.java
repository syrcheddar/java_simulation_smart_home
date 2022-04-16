package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class GoRunningEvent extends Event {
    public GoRunningEvent(Person person) {
        super(null, person, Constant.EVENT_GO_RUNNING_TIME, EventType.ENUM_GO_RUNNING_EVENT);
    }
}
