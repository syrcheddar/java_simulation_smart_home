package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class ListenToMusicEvent extends Event {

    public ListenToMusicEvent(Device device, Person person) {
        super(device, person, Constant.EVENT_LISTEN_TO_MUSIC_TIME, EventType.ENUM_LISTEN_TO_MUSIC);
    }
}
