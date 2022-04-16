package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.utils.Constant;

public class PetSleepEvent extends Event {
    public PetSleepEvent(Pet pet, Room room) {
        super(pet, Constant.PET_SLEEP_LENGTH, EventType.ENUM_PET_SLEEP_EVENT);
    }
}
