package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class HouseBoomEvent extends Event {
    public HouseBoomEvent() {
        super(null, null, Constant.EVENT_HOUSE_BOOM_TIME, EventType.ENUM_HOUSE_BOOM_EVENT);
    }
}
