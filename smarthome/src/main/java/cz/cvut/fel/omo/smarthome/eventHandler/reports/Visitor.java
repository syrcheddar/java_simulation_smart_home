package cz.cvut.fel.omo.smarthome.eventHandler.reports;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.Floor;
import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.building.Window;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.devices.utils.Documentation;
import cz.cvut.fel.omo.smarthome.devices.utils.PowerConsumption;
import cz.cvut.fel.omo.smarthome.devices.utils.WaterConsumption;
import cz.cvut.fel.omo.smarthome.eventHandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventHandler.events.Event;
import cz.cvut.fel.omo.smarthome.items.SportItem;
public interface Visitor {
    default void visitDevice(Device device) {
    }

    default void visitPerson(Person person) {
    }

    default void visitPet(Pet pet) {
    }

    default void visitHouse(House house) {
    }

    default void visitFloor(Floor floor) {
    }


    default void visitRoom(Room room) {
    }

    default void visitWindow(Window window) {
    }

    default void visitEventHandler(EventHandler eventHandler) {
    }

    default void visitItem(SportItem item) {
    }

    default void visitPowerConsumption(PowerConsumption powerConsumption) {
    }

    default void visitWaterConsumption(WaterConsumption waterConsumption) {
    }

    default void visitDocumentation(Documentation documentation) {
    }

    default void visitEvent(Event event) {
    }
}
