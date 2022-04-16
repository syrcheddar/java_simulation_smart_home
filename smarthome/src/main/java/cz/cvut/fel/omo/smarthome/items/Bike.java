package cz.cvut.fel.omo.smarthome.items;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.SportItemType;

public class Bike extends BaseItem {
    /**
     * Constructor for Bike Class
     * @param room Instance of room in which bike will spawn
     * @param name String name of bike, you can call it for example Bruno.
     */
    public Bike(Room room, String name) {
        super(SportItemType.ENUM_BIKE, room, name);
    }
}
