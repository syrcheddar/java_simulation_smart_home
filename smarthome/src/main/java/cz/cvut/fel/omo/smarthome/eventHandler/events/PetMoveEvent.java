package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class PetMoveEvent extends Event {
    private final Room roomToMove;

    public PetMoveEvent(Pet pet, Room room) {
        super(pet, Constant.EVENT_PET_MOVE_TIME, EventType.ENUM_PET_MOVE_EVENT);
        this.roomToMove = room;
    }

    public Room getRoom() {
        return roomToMove;
    }
}
