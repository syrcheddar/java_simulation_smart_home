package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class DIYRepairEvent extends Event {
    public DIYRepairEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_DIY_REPAIR_TIME, EventType.ENUM_DIY_REPAIR_EVENT);
    }
}
