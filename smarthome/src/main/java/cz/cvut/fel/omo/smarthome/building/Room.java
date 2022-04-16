package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.devices.Light;
import cz.cvut.fel.omo.smarthome.devices.Thermometer;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String roomName;
    private final List<Window> windows;
    private final List<SportItem> sportItems = new ArrayList<>();
    private final Thermometer thermometer;
    private final Light light;
    private Floor floor;
    private List<Person> peopleInRoom = new ArrayList<>();
    private List<Device> devices = new ArrayList<>();
    private List<Pet> petsInRoom = new ArrayList<>();

    /**
     * Constructor for new room.
     *
     * @param roomName String with name of room, for example Bedroom.
     * @param floor Object Floor, on this floor is the room, cant be null, it crushes.
     * @param windows ArrayList of objects Window - windows of the room.
     */
    public Room(String roomName, Floor floor, ArrayList<Window> windows) {
        this.roomName = roomName;
        this.floor = floor;
        this.windows = new ArrayList<>();
        floor.addRoom(this);
        thermometer = new Thermometer(IDHandler.getInstance().getNewID(), this);
        devices.add(thermometer);
        light = new Light(IDHandler.getInstance().getNewID(), this);
    }

    public List<SportItem> getSportItems() {
        return sportItems;
    }

    public Light getLight() {
        return light;
    }

    public String getRoomName() {
        return roomName;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public List<Person> getPeopleInRoom() {
        return peopleInRoom;
    }

    public void setPeopleInRoom(List<Person> peopleInRoom) {
        this.peopleInRoom = peopleInRoom;
    }

    public List<Pet> getPetsInRoom() {
        return petsInRoom;
    }

    public void setPetsInRoom(List<Pet> petsInRoom) {
        this.petsInRoom = petsInRoom;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<SportItem> getSportItem() {
        return sportItems;
    }

    public void addDevice(Device device) {
        devices.add(device);
        device.setRoom(this);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public void addPerson(Person person) {
        peopleInRoom.add(person);
    }

    public void removePerson(Person person) {
        peopleInRoom.remove(person);
    }

    public void addPet(Pet pet) {
        petsInRoom.add(pet);
    }

    public void removePet(Pet pet) {
        petsInRoom.remove(pet);
    }

    public void accept(Visitor visitor) {
        visitor.visitRoom(this);
    }

    public void addWindow(Window window) {
        windows.add(window);
    }

    public Thermometer getThermometer() {
        return thermometer;
    }

    public void addItem(SportItem item) {
        sportItems.add(item);
    }

    public void removeItem(SportItem item) {
        sportItems.remove(item);
    }
}
