package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class AirConditionerEvent extends Event {

    public AirConditionerEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_AC_TIME, EventType.ENUM_AC_EVENT);
    }
}
