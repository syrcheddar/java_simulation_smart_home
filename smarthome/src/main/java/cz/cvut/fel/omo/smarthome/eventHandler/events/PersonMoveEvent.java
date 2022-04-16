package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class PersonMoveEvent extends Event {
    private final Room roomToMove;

    public PersonMoveEvent(Person person, Room room) {
        super(null, person, Constant.EVENT_PERSON_MOVE_TIME, EventType.ENUM_PERSON_MOVE_EVENT);
        this.roomToMove = room;
    }

    public Room getRoom() {
        return roomToMove;
    }
}
