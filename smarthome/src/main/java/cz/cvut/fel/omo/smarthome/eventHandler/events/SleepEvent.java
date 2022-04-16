package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class SleepEvent extends Event {
    public SleepEvent(Person person) {
        super(null, person, Constant.SLEEP_LENGTH, EventType.ENUM_SLEEP_EVENT);
    }
}
