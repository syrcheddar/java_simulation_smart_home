package cz.cvut.fel.omo.smarthome.utils;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.*;
import cz.cvut.fel.omo.smarthome.building.builder.Builder;
import cz.cvut.fel.omo.smarthome.devices.DeviceFactory;
import cz.cvut.fel.omo.smarthome.enums.AnimalType;
import cz.cvut.fel.omo.smarthome.enums.Gender;
import cz.cvut.fel.omo.smarthome.items.Bike;
import cz.cvut.fel.omo.smarthome.items.Ski;
import cz.cvut.fel.omo.smarthome.items.SportItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private final House house;

    public DataLoader() {
        this.house = getConfig1();
        //this.house = getConfig2();
    }


    public House getBuiltHouse() {
        return this.house;
    }

    // House - small one-floor-house
    private House getConfig1() {
        Builder builder = new Builder();
        List<Floor> floors = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        List<SportItem> items = new ArrayList<>();

        //first floor
        Floor firstFloor = new Floor("first");
        floors.add(firstFloor);

        // Rooms (8)
        Room hall = new Room("Hall", firstFloor, null);

        Room livingRoom = new Room("livingRoom", firstFloor, null);

        Room kitchen = new Room("Kitchen", firstFloor, null);

        Room garage = new Room("Garage", firstFloor, null);

        Room bathroom = new Room("Bathroom", firstFloor, null);

        Room kidRoom1 = new Room("kidRoom1", firstFloor, null);

        Room kidRoom2 = new Room("kidRoom2", firstFloor, null);

        Room bedRoom = new Room("bedRoom", firstFloor, null);

        //DEVICES (21)

        //WashingMachine
        DeviceFactory.createWashingMachine(bathroom);

        //TV
        DeviceFactory.createTv(bedRoom);
        DeviceFactory.createTv(livingRoom);
        DeviceFactory.createTv(kidRoom1);
        DeviceFactory.createTv(kidRoom2);

        //Oven
        DeviceFactory.createOven(kitchen);

        //Fridge
        DeviceFactory.createFridge(kitchen);

        //CDPlayer
        DeviceFactory.createCDPlayer(kidRoom1);
        DeviceFactory.createCDPlayer(kidRoom2);
        DeviceFactory.createCDPlayer(livingRoom);

        //AirConditioner
        DeviceFactory.createAirConditioner(livingRoom);
        DeviceFactory.createAirConditioner(hall);

        //Microwave
        DeviceFactory.createMicrowave(kitchen);

        // Windows
        Window window1 = new Window(new Blinds());
        hall.addWindow(window1);

        Window window2 = new Window(new Blinds());
        bathroom.addWindow(window2);

        Window window3 = new Window(new Blinds());
        livingRoom.addWindow(window3);

        Window window4 = new Window(new Blinds());
        livingRoom.addWindow(window4);

        Window window5 = new Window(new Blinds());
        kitchen.addWindow(window5);

        Window window6 = new Window(new Blinds());
        garage.addWindow(window6);

        Window window7 = new Window(new Blinds());
        kidRoom1.addWindow(window7);

        Window window8 = new Window(new Blinds());
        kidRoom2.addWindow(window8);

        Window window9 = new Window(new Blinds());
        bedRoom.addWindow(window9);

        // People (6)
        Person dad = new Person("Dad", "Omo", LocalDate.parse("1971-01-02"), Gender.ENUM_MALE, bedRoom);
        Person mom = new Person("Mom", "Omo", LocalDate.parse("1972-03-04"), Gender.ENUM_FEMALE, bedRoom);
        Person child1 = new Person("Child1", "Omo", LocalDate.parse("2010-08-07"), Gender.ENUM_MALE, kidRoom1);
        Person child2 = new Person("Child2", "Omo", LocalDate.parse("2011-09-06"), Gender.ENUM_MALE, kidRoom1);
        Person child3 = new Person("Child3", "Omo", LocalDate.parse("2012-10-05"), Gender.ENUM_FEMALE, kidRoom2);
        Person child4 = new Person("Child4", "Omo", LocalDate.parse("2013-11-04"), Gender.ENUM_FEMALE, kidRoom2);

        persons.add(dad);
        persons.add(mom);
        persons.add(child1);
        persons.add(child2);
        persons.add(child3);
        persons.add(child4);
        // Pets (3)

        Pet dog = new Pet("Dog", LocalDate.parse("2015-12-11"), AnimalType.ENUM_DOG, Gender.ENUM_MALE, livingRoom);
        Pet cat = new Pet("Cat", LocalDate.parse("2016-10-09"), AnimalType.ENUM_CAT, Gender.ENUM_MALE, kidRoom2);
        //Pet Parrot = new Pet("Parrot", LocalDate.parse("1970-09-09"), AnimalType.Parrot,Gender.FEMALE,livingRoom);

        pets.add(dog);
        pets.add(cat);

        // Items(4)
        Ski ski1 = new Ski(garage, "Ski1");
        Ski ski2 = new Ski(garage, "Ski2");

        items.add(ski1);
        items.add(ski2);

        Bike bicycle1 = new Bike(garage, "Bike1");
        Bike bicycle2 = new Bike(garage, "Bike2");

        items.add(bicycle1);
        items.add(bicycle2);

        return builder.houseName("firstHouse").floors(floors).pets(pets).persons(persons)
                .devices().items(items).getResult();
    }

    // House - 2 floors
    private House getConfig2() {
        Builder builder = new Builder();
      List<Floor> floors = new ArrayList<>();
      List<Pet> pets = new ArrayList<>();
      List<Person> persons = new ArrayList<>();
      List<SportItem> items = new ArrayList<>();

        //first floor
        Floor firstFloor = new Floor("first");
        floors.add(firstFloor);

        // Rooms (4)
        Room hall = new Room("Hall", firstFloor, null);

        Room livingRoom = new Room("livingRoom", firstFloor, null);

        Room kitchen = new Room("Kitchen", firstFloor, null);

        Room garage = new Room("Garage", firstFloor, null);


        //second floor
        Floor secondFloor = new Floor("second");
        floors.add(secondFloor);

        //rooms (4)
        Room bathroom = new Room("Bathroom", secondFloor, null);

        Room kidRoom1 = new Room("kidRoom1", secondFloor, null);

        Room kidRoom2 = new Room("kidRoom2", secondFloor, null);

        Room bedRoom = new Room("bedRoom", secondFloor, null);

        //DEVICES (21)

        //WashingMachine
        DeviceFactory.createWashingMachine(bathroom);

        //TV
        DeviceFactory.createTv(bedRoom);
        DeviceFactory.createTv(livingRoom);
        DeviceFactory.createTv(kidRoom1);
        DeviceFactory.createTv(kidRoom2);

        //Oven
        DeviceFactory.createOven(kitchen);

        //Fridge
         DeviceFactory.createFridge(kitchen);

        //CDPlayer
        DeviceFactory.createCDPlayer(kidRoom1);
        DeviceFactory.createCDPlayer(kidRoom2);
        DeviceFactory.createCDPlayer(livingRoom);

        //AirConditioner
        DeviceFactory.createAirConditioner(livingRoom);
        DeviceFactory.createAirConditioner(hall);

        //Microwave
        DeviceFactory.createMicrowave(kitchen);

        //Light
        DeviceFactory.createLight(hall);
        DeviceFactory.createLight(bathroom);
        DeviceFactory.createLight(livingRoom);
        DeviceFactory.createLight(kitchen);
        DeviceFactory.createLight(garage);
        DeviceFactory.createLight(kidRoom1);
        DeviceFactory.createLight(kidRoom2);
        DeviceFactory.createLight(bedRoom);

        // Windows
        Window window1 = new Window(new Blinds());
        hall.addWindow(window1);

        Window window2 = new Window(new Blinds());
        bathroom.addWindow(window2);

        Window window3 = new Window(new Blinds());
        livingRoom.addWindow(window3);

        Window window4 = new Window(new Blinds());
        livingRoom.addWindow(window4);

        Window window5 = new Window(new Blinds());
        kitchen.addWindow(window5);

        Window window6 = new Window(new Blinds());
        garage.addWindow(window6);

        Window window7 = new Window(new Blinds());
        kidRoom1.addWindow(window7);

        Window window8 = new Window(new Blinds());
        kidRoom2.addWindow(window8);

        Window window9 = new Window(new Blinds());
        bedRoom.addWindow(window9);

        // People (6)
        Person dad = new Person("Dad", "Omo", LocalDate.parse("1971-01-02"), Gender.ENUM_MALE, bedRoom);
        Person mom = new Person("Mom", "Omo", LocalDate.parse("1972-03-04"), Gender.ENUM_FEMALE, bedRoom);
        Person child1 = new Person("Child1", "Omo", LocalDate.parse("2010-08-07"), Gender.ENUM_MALE, kidRoom1);
        Person child2 = new Person("Child2", "Omo", LocalDate.parse("2011-09-06"), Gender.ENUM_MALE, kidRoom1);
        Person child3 = new Person("Child3", "Omo", LocalDate.parse("2012-10-05"), Gender.ENUM_FEMALE, kidRoom2);
        Person child4 = new Person("Child4", "Omo", LocalDate.parse("2013-11-04"), Gender.ENUM_FEMALE, kidRoom2);

        persons.add(dad);
        persons.add(mom);
        persons.add(child1);
        persons.add(child2);
        persons.add(child3);
        persons.add(child4);
        // Pets (3)

        Pet dog = new Pet("Dog", LocalDate.parse("2015-12-11"), AnimalType.ENUM_DOG, Gender.ENUM_MALE, livingRoom);
        Pet cat = new Pet("Cat", LocalDate.parse("2016-10-09"), AnimalType.ENUM_CAT, Gender.ENUM_MALE, kidRoom2);
        //Pet Parrot = new Pet("Parrot", LocalDate.parse("1970-09-09"), AnimalType.Parrot,Gender.FEMALE,livingRoom);

        pets.add(dog);
        pets.add(cat);

        // Items(4)
        Ski ski1 = new Ski(garage, "Ski1");
        Ski ski2 = new Ski(garage, "Ski2");

        items.add(ski1);
        items.add(ski2);

        Bike bicycle1 = new Bike(garage, "Bike1");
        Bike bicycle2 = new Bike(garage, "Bike2");

        items.add(bicycle1);
        items.add(bicycle2);

        return builder.houseName("secondHouse").floors(floors).pets(pets).persons(persons)
                .devices().items(items).getResult();
    }


    /*private Skill getRandomPersonSkill() {
        List<Skill> skills = List.of(Skill.CAN_COOK, Skill.CAN_DRIVE, Skill.CAN_REPAIR, Skill.CAN_WASH);
        return skills.get(this.faker.random().nextInt(skills.size()));
    }

    private List<String> getTVChannels() {
        return List.of("CT1", "CT2", "Nova", "Prima");
    }

    private List<String> getRadioChannels() {
        return List.of("Kiss", "Blanik", "Evropa2", "Spin");
    }*/

}

