package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class MicrowaveEvent extends Event {
    public MicrowaveEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_MICROWAVE_TIME, EventType.ENUM_MICROWAVE_EVENT);
    }
}

