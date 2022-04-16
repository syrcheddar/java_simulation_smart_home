package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class WorkEvent extends Event {
    public WorkEvent(Person person) {
        super(null, person, Constant.WORK_LENGTH, EventType.ENUM_WORK_EVENT);
    }
}