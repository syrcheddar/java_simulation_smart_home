package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.eventHandler.Observer;
import cz.cvut.fel.omo.smarthome.eventHandler.events.*;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class House implements Observer {
    private String houseName;
    private List<Floor> floors = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private List<Pet> pets = new ArrayList<>();
    private List<Device> devices = new ArrayList<>();
    private List<SportItem> items = new ArrayList<>();
    private List<Device> destroyedDevices = new ArrayList<>();

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Person> getPeople() {
        return persons;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<SportItem> getItems() {
        return items;
    }

    public void setItems(List<SportItem> items) {
        this.items = items;
    }
    public void addDestroyedDevice(Device device){
        destroyedDevices.add(device);
    }

    public List<Device> getDestroyedDevices(){
        return destroyedDevices;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addItem(SportItem item) {
        items.add(item);
    }

    public boolean allLightsOn() {
        boolean areOn = true;
        for (Device d : devices) {
            if (d.getType() == DeviceType.ENUM_LIGHT) {
                if (!d.getState().isBeingUsed()) areOn = false;
                break;
            }
        }
        for (Floor f : floors) {
            for (Room r : f.getRooms()) {
                if (r.getLight().getState().isOff()) {
                    areOn = false;
                    break;
                }
            }
        }
        return areOn;
    }

    public boolean allLightsOff() {
        boolean areOff = true;
        for (Device d : devices) {
            if (d.getType() == DeviceType.ENUM_LIGHT) {
                if (d.getState().isBeingUsed()) areOff = false;
                break;
            }
        }
        for (Floor f : floors) {
            for (Room r : f.getRooms()) {
                if (!r.getLight().getState().isOff()) {
                    areOff = false;
                    break;
                }
            }
        }
        return areOff;
    }

    public boolean getBlindsState() {
        boolean areOpened = true;
        for (Floor f : floors) {
            for (Room r : f.getRooms()) {
                for (Window w : r.getWindows()) {
                    if (!w.getBlinds().areOpened()) {
                        areOpened = false;
                        break;
                    }
                }
            }
        }
        return areOpened;
    }

    public void removeItem(SportItem item) {
        items.remove(item);
    }

    public void setDevices() {
        for (Floor floor : getFloors()) {
            for (Room room : floor.getRooms()) {
                devices.addAll(room.getDevices());
            }
        }
    }

    public Event getNextStep() {
        Random random = new Random();
        int number = random.nextInt(100000);
        if (number == 1) {
            Event e = new HouseBoomEvent();
            doEvent(e);
            return e;
        }
        return null;
    }

    @Override
    public void doEvent(Event event) {
        switch (event.getEventType()) {
            case ENUM_HOUSE_BOOM_EVENT -> doEvent((HouseBoomEvent) event);
            case ENUM_LIGHT_TURN_OFF -> doEvent((LightTurnOffEvent) event);
            case ENUM_LIGHT_TURN_ON -> doEvent((LightTurnOnEvent) event);
            case ENUM_CLOSE_BLINDS_EVENT -> doEvent((CloseBlindsEvent) event);
            case ENUM_OPEN_BLINDS_EVENT -> doEvent((OpenBlindsEvent) event);
        }
    }

    @Override
    public void doEvent(AirConditionerEvent event) {
        for (Floor floor : floors) {
            floor.getRooms().forEach(r -> {
                if (r.getThermometer().getTemperature() < Constant.AC_DOWN_TEMP || r.getThermometer().getTemperature() > Constant.AC_UP_TEMP) {
                    event.getDevice().getState().useDevice(event.getDevice(), null);
                }
            });
        }
    }

    @Override
    public void doEvent(HouseBoomEvent event) {
        this.floors = new ArrayList<>();
        this.devices = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.items = new ArrayList<>();
        this.pets = new ArrayList<>();
        this.houseName = "Explosion";
    }

    @Override
    public void doEvent(LightTurnOffEvent event) {
        this.floors.forEach(floor -> floor.getRooms().forEach(room -> room.getLight().getState().turnOffDevice(room.getLight())));
    }

    @Override
    public void doEvent(LightTurnOnEvent event) {
        this.floors.forEach(floor -> floor.getRooms().forEach(room -> {
            room.getLight().getState().turnOnDevice(room.getLight());
            room.getLight().getState().useDevice(room.getLight(), null);
        }));
    }

    @Override
    public void doEvent(CloseBlindsEvent event) {
        this.floors.forEach(floor -> floor.getRooms().forEach(room -> room.getWindows().forEach(window -> window.getBlinds().close())));
    }

    @Override
    public void doEvent(OpenBlindsEvent event) {
        this.floors.forEach(floor -> floor.getRooms().forEach(room -> room.getWindows().forEach(window -> window.getBlinds().open())));
    }

    public void accept(Visitor visitor) {
        visitor.visitHouse(this);
    }

}
