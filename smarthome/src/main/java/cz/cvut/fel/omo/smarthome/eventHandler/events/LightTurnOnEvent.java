package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class LightTurnOnEvent extends Event {
    public LightTurnOnEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_LIGHT_TURN_ON_TIME, EventType.ENUM_LIGHT_TURN_ON);
    }
}
