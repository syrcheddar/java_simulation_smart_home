package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class ServiceRepairEvent extends Event {
    public ServiceRepairEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_SERVICE_REPAIR_TIME, EventType.ENUM_SERVICE_REPAIR);
    }
}
