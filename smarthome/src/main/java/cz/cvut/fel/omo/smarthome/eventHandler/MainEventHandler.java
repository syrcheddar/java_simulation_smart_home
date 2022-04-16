package cz.cvut.fel.omo.smarthome.eventHandler;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.devices.DeviceFactory;
import cz.cvut.fel.omo.smarthome.enums.Activity;
import cz.cvut.fel.omo.smarthome.enums.EventType;
import cz.cvut.fel.omo.smarthome.enums.PartOfDay;
import cz.cvut.fel.omo.smarthome.eventHandler.events.*;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class MainEventHandler implements EventHandler {


    private static MainEventHandler instance;
    private final List<Observer> observers = new ArrayList<>();
    private final List<Event> allEndedEvents = new ArrayList<>();
    private final List<Event> allActiveEvents = new ArrayList<>();
    private List<FinishEvent> finishEventList = new ArrayList<>();
    private House house;
    private int time;
    private int peopleNumber=0;

    private MainEventHandler() {
        time = 0;
    }

    public static EventHandler getInstance() {
        if (instance == null) {
            instance = new MainEventHandler();
        }
        return instance;
    }

    public static MainEventHandler getInstance(House newHouse) {
        if (instance == null) {
            instance = new MainEventHandler();
        }

        if (instance.house == null) {
            instance.house = newHouse;
        }
        return instance;
    }

    public int getTime() {
        return time;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<Event> getAllActiveEvents() {
        return allActiveEvents;
    }

    public List<FinishEvent> getFinishEventList() {
        return finishEventList;
    }

    public PartOfDay getPartOfDay() {
        int time = this.time / (60);
        while (time > 23) time -= 24;
        if ((time >= 6 && time < 12)) {
            return PartOfDay.ENUM_MORNING;
        }
        if (time >= 18) {
            return PartOfDay.ENUM_EVENING;
        }
        if (time >= 0 && time < 6) {
            return PartOfDay.ENUM_NIGHT;
        }
        return PartOfDay.ENUM_AFTERNOON;
    }

    private void moveTime() {
        time += Constant.TIME_TICK;
    }

    public void begin(int days) {
        setup();
        while (true) {
            //going sleep
            if (getPartOfDay() == PartOfDay.ENUM_NIGHT) {
                for (Person person : house.getPeople()) {
                    if (person.getActivity() == Activity.ENUM_FREE) {
                        addEvent(new SleepEvent(person));
                    }
                }
                //going to work
            } else if (getPartOfDay() == PartOfDay.ENUM_MORNING) {
                for (Person person : house.getPeople()) {
                    if (person.getActivity() == Activity.ENUM_FREE) {
                        if(person.getAge()>18){
                            addEvent(new WorkEvent(person));
                        }
                        else{
                            addEvent(person.getNextStep());
                        }
                    }
                }
            } else {
                //finding next activity
                for (Person person : house.getPeople()) {
                    if (person.getActivity() == Activity.ENUM_FREE) {
                        addEvent(person.getNextStep());
                    }
                }
            }
            //finding next activity
            for (Pet pet : house.getPets()) {
                if (pet.getActivity() == Activity.ENUM_FREE) {
                    addEvent(pet.getNextPetStep());
                }
            }
            //house controlling lights
            if (getPartOfDay() == PartOfDay.ENUM_AFTERNOON && !house.allLightsOn()) {
                house.getFloors().forEach(f -> f.getRooms().forEach(d -> {
                    if (d.getLight().getState().isOff()) addEvent(new LightTurnOnEvent(d.getLight(), null));
                }));
            }
            //house controlling lights
            if (getPartOfDay() == PartOfDay.ENUM_NIGHT && !house.allLightsOff()) {
                boolean everybodySleeps = true;
                for (Person p : house.getPeople()) {
                    if (!p.getActivity().equals(Activity.ENUM_SLEEPING)) {
                        everybodySleeps = false;
                        break;
                    }
                }
                if (everybodySleeps) {
                    house.getFloors().forEach(f -> f.getRooms().forEach(d -> {
                        if (d.getLight().getState().isBeingUsed()) addEvent(new LightTurnOffEvent(d.getLight(), null));
                    }));
                }
            }
            //house controlling blinds
            if (getPartOfDay() == PartOfDay.ENUM_NIGHT && !house.getBlindsState()) {
                addEvent(new CloseBlindsEvent(null, null));
            }
            //house controlling blinds
            if (getPartOfDay() == PartOfDay.ENUM_MORNING && house.getBlindsState()) {
                addEvent(new OpenBlindsEvent(null, null));
            }
            //house controlling lights
            if (getPartOfDay() == PartOfDay.ENUM_MORNING && !house.allLightsOff()) {
                house.getFloors().forEach(f -> f.getRooms().forEach(d -> {
                    if (d.getLight().getState().isBeingUsed()) addEvent(new LightTurnOffEvent(d.getLight(), null));
                }));
            }
            //easterEgg
            Event event = house.getNextStep();
            if (event != null) {
                addEvent(event);
                if (event.getEventType() == EventType.ENUM_HOUSE_BOOM_EVENT) {
                    System.out.println("Gratulujeme, vybouchli jste!");
                    break;
                }
            }
            //doing events
            allActiveEvents.forEach(e -> {
                //DeviceLifetime - 1
                if(e.getDevice()!=null){
                    if (e.getDevice().getLifeTime()>0) {
                        e.getDevice().setLifeTime(e.getDevice().getLifeTime() - 1);
                    }
                }
                if (!e.isInProgress()) {
                    if (e.getPerson() != null) {
                        e.getPerson().doEvent(e);
                    } else if (e.getPet() != null) {
                        e.getPet().doEvent(e);
                    } else {
                        house.doEvent(e);
                    }
                    e.setInProgress(true);
                }
                e.subtractTime(10);
                if (e.getTime() < 0) {
                    addFinishEvent(new FinishEvent(e));
                }
            });
            house.getDevices().forEach(d -> d.getSensor().update());
            house.getFloors().forEach(f -> f.getRooms().forEach(r -> r.getLight().getSensor().update()));
            //finishing events
            finishEventList.forEach(e -> {
                allActiveEvents.remove(e.getEvent());
                allEndedEvents.add(e.getEvent());
            });
            finishEvents(finishEventList);
            moveTime();
            if (time > (days * 24 * 60)){
                if (peopleNumber==house.getPeople().size()){
                    break;
                }
            }
        }

    }


    private void finishEvents(List<FinishEvent> finishEventList) {
        for (FinishEvent fin : finishEventList) {
            Event e = fin.getEvent();
            switch (e.getEventType()) {
                case ENUM_DIY_REPAIR_EVENT -> {
                    DIYRepairEvent event = (DIYRepairEvent) e;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    event.getDevice().unreserve();
                    event.getDevice().getState().repairDevice(event.getDevice(), event.getPerson());
                    if(event.getDevice().getLifeTime()<500){
                        event.getDevice().setLifeTime(event.getDevice().getLifeTime()+200);
                    }
                    else if(event.getDevice().getLifeTime()<850){
                        event.getDevice().setLifeTime(event.getDevice().getLifeTime()+50);
                    }
                    else if(event.getDevice().getLifeTime()<950){
                        event.getDevice().setLifeTime(event.getDevice().getLifeTime()+10);
                    }
                }
                case ENUM_WASHING_MACHINE_EVENT -> {
                    WashingMachineEvent event = (WashingMachineEvent) e;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    event.getDevice().unreserve();
                    event.getDevice().getState().turnOffDevice(event.getDevice());
                }
                case ENUM_FRIDGE_EVENT -> {
                    FridgeEvent event = (FridgeEvent) e;
                    event.getDevice().unreserve();
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    event.getDevice().getState().stopUsingDevice(event.getDevice(), event.getPerson());
                }
                case ENUM_BUY_EVENT -> {
                    BuyEvent event = (BuyEvent) e;
                    Device device;
                    switch (event.getDevice().getType()) {
                        case ENUM_AIR_CONDITIONER -> device = DeviceFactory.createAirConditioner(event.getRoom());
                        case ENUM_CD_PLAYER -> device = DeviceFactory.createCDPlayer(event.getRoom());
                        case ENUM_FRIDGE -> device = DeviceFactory.createFridge(event.getRoom());
                        case ENUM_TELEVISION -> device = DeviceFactory.createTv(event.getRoom());
                        case ENUM_WASHING_MACHINE -> device = DeviceFactory.createWashingMachine(event.getRoom());
                        case ENUM_OVEN -> device = DeviceFactory.createOven(event.getRoom());
                        case ENUM_LIGHT -> device = DeviceFactory.createLight(event.getRoom());
                        case ENUM_MICROWAVE -> device = DeviceFactory.createMicrowave(event.getRoom());
                        case ENUM_THERMOMETER -> device = DeviceFactory.createThermometer(event.getRoom());
                        default -> device = null;
                    }
                    assert device != null;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    house.addPerson(event.getPerson());
                    event.getRoom().addPerson(event.getPerson());
                    event.getPerson().setRoom(event.getRoom());
                    event.getRoom().addDevice(device);
                    house.addDevice(device);
                    device.setRoom(event.getRoom());

                }
                case ENUM_SERVICE_REPAIR -> {
                    ServiceRepairEvent event = (ServiceRepairEvent) e;
                    event.getDevice().unreserve();
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    house.addPerson(event.getPerson());
                    house.addDevice(event.getDevice());
                    event.getRoom().addPerson(event.getPerson());
                    event.getPerson().setRoom(event.getRoom());
                    event.getRoom().addDevice(event.getDevice());
                    event.getDevice().setRoom(event.getRoom());
                    event.getDevice().getState().repairDevice(event.getDevice(), event.getPerson());
                    if(event.getDevice().getLifeTime()<500){
                        event.getDevice().setLifeTime(event.getDevice().getLifeTime()+400);
                    }
                    else if(event.getDevice().getLifeTime()<850){
                        event.getDevice().setLifeTime(event.getDevice().getLifeTime()+100);
                    }
                    else if(event.getDevice().getLifeTime()<950){
                        event.getDevice().setLifeTime(event.getDevice().getLifeTime()+30);
                    }
                }
                case ENUM_WATCH_TV -> {
                    WatchTVEvent event = (WatchTVEvent) e;
                    event.getDevice().unreserve();
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    event.getDevice().getState().turnOffDevice(event.getDevice());
                }
                case ENUM_LISTEN_TO_MUSIC -> {
                    ListenToMusicEvent event = (ListenToMusicEvent) e;
                    event.getDevice().unreserve();
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    event.getDevice().getState().turnOffDevice(event.getDevice());
                }
                case ENUM_LIGHT_TURN_ON -> {
                    LightTurnOnEvent event = (LightTurnOnEvent) e;
                    event.getDevice().unreserve();
                    event.getDevice().getState().turnOnDevice(event.getDevice());
                    event.getDevice().getState().useDevice(event.getDevice(), event.getPerson());
                }
                case ENUM_AC_EVENT -> {
                    AirConditionerEvent event = (AirConditionerEvent) e;
                    event.getDevice().unreserve();
                    event.getDevice().getState().stopUsingDevice(event.getDevice(), null);
                }
                case ENUM_LIGHT_TURN_OFF -> {
                    LightTurnOffEvent event = (LightTurnOffEvent) e;
                    event.getDevice().unreserve();
                    event.getDevice().getState().turnOffDevice(event.getDevice());
                }
                case ENUM_GO_SKIING_EVENT -> {
                    GoSkiingEvent event = (GoSkiingEvent) e;
                    event.getSportItem().unreserve();
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    house.addPerson(event.getPerson());
                    house.addItem(event.getSportItem());
                    event.getRoom().addPerson(event.getPerson());
                    event.getPerson().setRoom(event.getRoom());
                    event.getRoom().addItem(event.getSportItem());
                    event.getSportItem().setRoom(event.getRoom());
                }
                case ENUM_GO_BIKING_EVENT -> {
                    GoBikingEvent event = (GoBikingEvent) e;
                    event.getSportItem().unreserve();
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    house.addPerson(event.getPerson());
                    house.addItem(event.getSportItem());
                    event.getRoom().addPerson(event.getPerson());
                    event.getPerson().setRoom(event.getRoom());
                    event.getRoom().addItem(event.getSportItem());
                    event.getSportItem().setRoom(event.getRoom());
                }
                case ENUM_GO_RUNNING_EVENT -> {
                    GoRunningEvent event = (GoRunningEvent) e;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    house.addPerson(event.getPerson());
                    event.getRoom().addPerson(event.getPerson());
                    event.getPerson().setRoom(event.getRoom());
                }
                case ENUM_OVEN_EVENT -> {
                    OvenEvent event = (OvenEvent) e;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    event.getDevice().unreserve();
                    event.getDevice().getState().stopUsingDevice(event.getDevice(), event.getPerson());
                }
                case ENUM_SLEEP_EVENT -> {
                    SleepEvent event = (SleepEvent) e;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                }
                case ENUM_PET_SLEEP_EVENT -> {
                    PetSleepEvent event = (PetSleepEvent) e;
                    event.getPet().setActivity(Activity.ENUM_FREE);
                }
                case ENUM_WORK_EVENT -> {
                    WorkEvent event = (WorkEvent) e;
                    event.getPerson().setActivity(Activity.ENUM_FREE);
                    house.addPerson(event.getPerson());
                    event.getPerson().setRoom(house.getFloors().get(0).getRooms().get(0));
                    event.getPerson().getRoom().addPerson(event.getPerson());
                }
            }
        }
        this.finishEventList = new ArrayList<>();
    }

    private void setup() {
        house.getPeople().forEach(this::observe);
        peopleNumber=house.getPeople().size();
        house.getPets().forEach(this::observe);

    }


    @Override
    public void observe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void stopObserve(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public List<Event> getAllEndedEvents() {
        return allEndedEvents;
    }

    @Override
    public void Accept(Visitor visitor) {
        visitor.visitEventHandler(this);
    }

    @Override
    public House getHouse() {
        return house;
    }

    public void addFinishEvent(FinishEvent event) {
        finishEventList.add(event);
    }

    public void addEvent(Event event) {
        allActiveEvents.add(event);
    }
}
