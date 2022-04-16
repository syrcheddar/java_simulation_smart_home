package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class LightTurnOffEvent extends Event {

    public LightTurnOffEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_LIGHT_TURN_OFF_TIME, EventType.ENUM_LIGHT_TURN_OFF);
    }
}
