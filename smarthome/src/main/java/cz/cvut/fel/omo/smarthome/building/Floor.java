package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final String name;
    private final List<Room> rooms = new ArrayList<>();
    private House house;

    public Floor(String name) {
        this.name = name;
    }

    public House getHouse() {
        return this.house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        room.setFloor(this);
        rooms.add(room);
    }

    public String getName() {
        return name;
    }

    public void accept(Visitor visitor) {
        visitor.visitFloor(this);
    }
}
