package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class BuyEvent extends Event {
    public BuyEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_BUY_TIME, EventType.ENUM_BUY_EVENT);
    }
}
