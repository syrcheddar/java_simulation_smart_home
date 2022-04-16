package cz.cvut.fel.omo.smarthome.items;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.SportItemType;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public abstract class BaseItem implements SportItem {
    protected final double id;
    protected final String name;
    private final SportItemType itemType;
    protected Room room;
    private boolean reserved = false;

    public BaseItem(SportItemType itemType, Room room, String name) {
        this.itemType = itemType;
        this.id = IDHandler.getInstance().getNewID();
        this.room = room;
        this.name = name;
        room.addItem(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitItem(this);
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public double getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public SportItemType getItemType() {
        return itemType;
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }

    @Override
    public void reserve() {
        this.reserved = true;
    }

    @Override
    public void unreserve() {
        this.reserved = false;
    }

    @Override
    public void cancelReservation() {
        this.reserved = false;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + getItemType() + '\'' +
                ", id =" + getId() +
                ", room =" + getRoom() +
                '}';
    }
}
