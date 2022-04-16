package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class WatchTVEvent extends Event {
    public WatchTVEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_WATCH_TV_TIME, EventType.ENUM_WATCH_TV);
    }
}
