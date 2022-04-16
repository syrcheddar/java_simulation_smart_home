package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class WashingMachineEvent extends Event {
    public WashingMachineEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_WASHING_MACHINE, EventType.ENUM_WASHING_MACHINE_EVENT);
    }
}
