package cz.cvut.fel.omo.smarthome.items;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.SportItemType;

public class Ski extends BaseItem {
    /**
     * Constructor for ski Class
     * @param room Instance of room in which skis will spawn
     * @param name String name of skis, you can call them for example Hubert.
     */
    public Ski(Room room, String name) {
        super(SportItemType.ENUM_SKI, room, name);
    }
}
