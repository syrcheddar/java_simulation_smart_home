package cz.cvut.fel.omo.smarthome.building.builder;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.Floor;
import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.items.SportItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for whole house from arrays of things you've already created. Merging and mushing it all together like a cake created by Cat and Dog.
 */
public class Builder {
    private final House house = new House();

    /**
     * Method for setting the name of house
     * @param houseName String name of house
     * @return Returns same instance of builder.
     */
    public Builder houseName(String houseName) {
        house.setHouseName(houseName);
        return this;
    }

    /**
     * Method for setting floors of house.
     * @param floors ArrayList of floors
     * @return Returns same instance of builder.
     */
    public Builder floors(List<Floor> floors) {
        house.setFloors(floors);
        floors.forEach(floor -> floor.setHouse(house));
        return this;
    }

    /**
     * Method for setting people in house.
     * @param persons ArrayList of people wanted in house.
     * @return Returns same instance of builder.
     */
    public Builder persons(List<Person> persons) {
        house.setPersons(persons);
        return this;
    }

    /**
     * Method for setting pets in house.
     * @param pets ArrayList of pets wanted in house.
     * @return Returns same instance of builder.
     */
    public Builder pets(List<Pet> pets) {
        house.setPets(pets);
        return this;
    }

    /**
     * Method for setting devices in house.
     * House is smart enough to go through all the rooms and finding all devices. House must have set rooms!!!
     * @return Returns same instance of builder.
     */
    public Builder devices() {
        house.setDevices();
        return this;
    }

    /**
     * Method for setting sport items in house.
     * @param items ArrayList of sport items wanted in house.
     * @return Returns same instance of builder.
     */
    public Builder items(List<SportItem> items) {
        house.setItems(items);
        return this;
    }

    /**
     * Method for returning house from Builder
     * @return Returns instance of House.
     */
    public House getResult() {
        return house;
    }

}
