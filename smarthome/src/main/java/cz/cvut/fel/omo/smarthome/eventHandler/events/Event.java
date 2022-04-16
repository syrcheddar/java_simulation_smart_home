package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.eventHandler.MainEventHandler;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {

    private final int startTime;
    protected Device device;
    protected Person person;
    protected Pet pet;
    protected int time;
    protected EventType eventType;
    protected Room room;
    private boolean inProgress = false;

    public Event(Device device, Person person, int time, EventType eventType) {
        this.device = device;
        this.person = person;
        this.time = time;
        this.startTime = MainEventHandler.getInstance().getTime();
        this.eventType = eventType;
        if (device != null) {
            this.room = device.getRoom();
        } else if (person != null) room = person.getRoom();
        else room = null;
    }

    public Event(Pet pet, int time, EventType eventType) {
        this.pet = pet;
        this.time = time;
        this.eventType = eventType;
        this.startTime = MainEventHandler.getInstance().getTime();
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean progress) {
        inProgress = progress;
    }

    public int getStartTime() {
        return startTime;
    }

    public void accept(Visitor visitor) {
        visitor.visitEvent(this);
    }

    public Device getDevice() {
        return device;
    }

    public Person getPerson() {
        return person;
    }

    public int getTime() {
        return time;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void subtractTime(int time) {
        this.time -= time;
    }

    public Pet getPet() {
        return pet;
    }

    public Room getRoom() {
        return room;
    }
}
