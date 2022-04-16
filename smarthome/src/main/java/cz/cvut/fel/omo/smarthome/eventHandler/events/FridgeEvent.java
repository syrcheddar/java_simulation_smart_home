package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class FridgeEvent extends Event {
    public FridgeEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_FRIDGE_USE_TIME, EventType.ENUM_FRIDGE_EVENT);
    }
}
