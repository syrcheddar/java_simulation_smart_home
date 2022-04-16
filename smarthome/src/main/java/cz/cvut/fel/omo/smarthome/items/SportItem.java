package cz.cvut.fel.omo.smarthome.items;


import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.SportItemType;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

public interface SportItem {
    void accept(Visitor visitor);

    Room getRoom();

    void setRoom(Room room);

    String getName();

    double getId();

    SportItemType getItemType();

    void reserve();

    void unreserve();

    void cancelReservation();

    boolean isReserved();

}
