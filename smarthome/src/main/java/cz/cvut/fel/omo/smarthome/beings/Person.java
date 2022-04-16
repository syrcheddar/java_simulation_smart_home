package cz.cvut.fel.omo.smarthome.beings;

import cz.cvut.fel.omo.smarthome.building.Floor;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.building.Window;
import cz.cvut.fel.omo.smarthome.devices.*;
import cz.cvut.fel.omo.smarthome.enums.Activity;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.enums.Gender;
import cz.cvut.fel.omo.smarthome.enums.SportItemType;
import cz.cvut.fel.omo.smarthome.eventHandler.events.*;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.items.SportItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class Person controlls as in real life most of work. Has methods for getting next event...
 */
public class Person extends Being {
    private final String surName;

    /**
     * Constructor for person
     * @param name String first name of person
     * @param surName String Surname of person
     * @param birthDate LocalDate his birthdate
     * @param gender Enum gender
     * @param room Instance of room in which he will spawn
     */
    public Person(String name, String surName, LocalDate birthDate, Gender gender, Room room) {
        super(name, gender, birthDate, room);
        this.surName = surName;
        room.addPerson(this);
    }

    public String getSurName() {
        return surName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void accept(Visitor visitor) {
        visitor.visitPerson(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + this.getName() + '\'' +
                ", id =" + this.getID() +
                ", room =" + this.getRoom() +
                '}';
    }

    public Person findPersonForRepair() {
        int requiredAge = 18;
        if (this.getAge() > requiredAge) {
            if (this.getActivity().equals(Activity.ENUM_FREE))
                return this;
        }
        return null;
    }

    public Event getNextStep() {
        Event step = new PersonMoveEvent(this, this.room);
        boolean mustRepair = false;
        for (Device device : room.getFloor().getHouse().getDevices()) {
            if (device.isDeviceBroken()) {
                if (findPersonForRepair() != null) {
                    int activity = ThreadLocalRandom.current().nextInt(0, 3);
                    switch (activity) {
                        //DIYRepair Event
                        case 0 -> {
                            if (!device.isReserved()) {
                                device.reserve();
                                step = new DIYRepairEvent(device, this);
                                mustRepair = true;
                            }
                        }

                        //Buy Event
                        case 1 -> {
                            if (!device.isReserved()) {
                                device.reserve();
                                step = new BuyEvent(device, this);
                                mustRepair = true;
                            }
                        }

                        //ServiceRepairEvent
                        case 2 -> {
                            if (!device.isReserved()) {
                                device.reserve();
                                step = new ServiceRepairEvent(device, this);
                                mustRepair = true;
                            }
                        }
                    }
                    break;
                }
            }
        }
        if (!mustRepair) {
            boolean activityType = ThreadLocalRandom.current().nextBoolean();
            if (activityType) {
                int activity = ThreadLocalRandom.current().nextInt(0, 3);
                switch (activity) {
                    //bike
                    case 0 -> {
                        List<SportItem> items = new ArrayList<>();
                        for (SportItem i : this.room.getFloor().getHouse().getItems()) {
                            if (i.getItemType() == SportItemType.ENUM_BIKE) {
                                items.add(i);
                            }
                        }
                        if (!items.isEmpty()) {
                            SportItem item = items.get(ThreadLocalRandom.current().nextInt(items.size()));
                            if (!item.isReserved()) {
                                item.reserve();
                                step = new GoBikingEvent(this, item);
                            } else {
                                if (items.size() > 1) {
                                    for (SportItem i : items) {
                                        if (!i.isReserved()) {
                                            i.reserve();
                                            step = new GoBikingEvent(this, i);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //ski
                    case 1 -> {
                        List<SportItem> items = new ArrayList<>();
                        for (SportItem i : this.room.getFloor().getHouse().getItems()) {
                            if (i.getItemType() == SportItemType.ENUM_SKI) {
                                items.add(i);
                            }
                        }
                        if (!items.isEmpty()) {
                            SportItem item = items.get(ThreadLocalRandom.current().nextInt(items.size()));
                            if (!item.isReserved()) {
                                item.reserve();
                                step = new GoSkiingEvent(this, item);
                            } else {
                                if (items.size() > 1) {
                                    for (SportItem i : items) {
                                        if (!i.isReserved()) {
                                            i.reserve();
                                            step = new GoSkiingEvent(this, i);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //running
                    case 2 -> step = new GoRunningEvent(this);
                    default -> step = new PersonMoveEvent(this, this.room);
                }
            } else {
                int activity = ThreadLocalRandom.current().nextInt(0, 11);
                switch (activity) {
                    //watch tv
                    case 1 -> {
                        List<Television> televisions = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_TELEVISION) {

                                televisions.add((Television) i);
                            }
                        }
                        if (televisions.isEmpty()) break;
                        Television device = televisions.get(ThreadLocalRandom.current().nextInt(televisions.size()));
                        if (!device.isReserved()) {
                            device.reserve();
                            step = new WatchTVEvent(device, this);
                        } else {
                            if (televisions.size() > 1) {
                                for (Television i : televisions) {
                                    if (!i.isReserved()) {
                                        i.reserve();
                                        step = new WatchTVEvent(i, this);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //watch fridge
                    case 2 -> {
                        List<Fridge> fridges = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_FRIDGE) {

                                fridges.add((Fridge) i);
                            }
                        }
                        if (fridges.isEmpty()) break;
                        Fridge device = fridges.get(ThreadLocalRandom.current().nextInt(fridges.size()));
                        if (!device.isReserved()) {
                            device.reserve();
                            step = new FridgeEvent(device, this);
                        } else {
                            if (fridges.size() > 1) {
                                for (Fridge i : fridges) {
                                    if (!i.isReserved()) {
                                        i.reserve();
                                        step = new FridgeEvent(i, this);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // do oven
                    case 3 -> {
                        List<Oven> ovens = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_OVEN) {

                                ovens.add((Oven) i);
                            }
                        }
                        if (ovens.isEmpty()) break;
                        Oven device = ovens.get(ThreadLocalRandom.current().nextInt(ovens.size()));
                        if (!device.isReserved()) {
                            device.reserve();
                            step = new OvenEvent(device, this);
                        } else {
                            if (ovens.size() > 1) {
                                for (Oven i : ovens) {
                                    if (!i.isReserved()) {
                                        i.reserve();
                                        step = new OvenEvent(i, this);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //listen to music
                    case 4 -> {
                        List<CDPlayer> cdPlayers = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_CD_PLAYER) {

                                cdPlayers.add((CDPlayer) i);
                            }
                        }
                        if (cdPlayers.isEmpty()) break;
                        CDPlayer device = cdPlayers.get(ThreadLocalRandom.current().nextInt(cdPlayers.size()));
                        if (!device.isReserved()) {
                            device.reserve();
                            step = new ListenToMusicEvent(device, this);
                        } else {
                            if (cdPlayers.size() > 1) {
                                for (CDPlayer i : cdPlayers) {
                                    if (!i.isReserved()) {
                                        i.reserve();
                                        step = new ListenToMusicEvent(i, this);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //washing machine
                    case 5 -> {
                        List<WashingMachine> washingMachines = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_WASHING_MACHINE) {
                                washingMachines.add((WashingMachine) i);
                            }
                        }
                        if (washingMachines.isEmpty()) break;
                        WashingMachine device = washingMachines.get(ThreadLocalRandom.current().nextInt(washingMachines.size()));
                        if (!device.isReserved()) {
                            device.reserve();
                            step = new WashingMachineEvent(device, this);
                        } else {
                            if (washingMachines.size() > 1) {
                                for (WashingMachine i : washingMachines) {
                                    if (!i.isReserved()) {
                                        i.reserve();
                                        step = new WashingMachineEvent(i, this);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case 6 -> {
                        List<Microwave> microwave = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_MICROWAVE) {
                                microwave.add((Microwave) i);
                            }
                        }
                        if (microwave.isEmpty()) break;
                        Microwave device = microwave.get(ThreadLocalRandom.current().nextInt(microwave.size()));
                        if (!device.isReserved()) {
                            device.reserve();
                            step = new MicrowaveEvent(device, this);
                        } else {
                            if (microwave.size() > 1) {
                                for (Microwave i : microwave) {
                                    if (!i.isReserved()) {
                                        i.reserve();
                                        step = new MicrowaveEvent(i, this);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case 7 -> {
                        List<Light> lights = new ArrayList<>();
                        for (Device i : this.room.getFloor().getHouse().getDevices()) {
                            if (i.getType() == DeviceType.ENUM_LIGHT) {
                                lights.add((Light) i);
                            }
                        }
                        if (lights.isEmpty()) break;
                        Light device = lights.get(ThreadLocalRandom.current().nextInt(lights.size()));
                        if (!device.isReserved()) {
                            if (device.getState().isBeingUsed()) {
                                step = new LightTurnOffEvent(device, this);
                                device.reserve();
                            } else {
                                step = new LightTurnOffEvent(device, this);
                                device.reserve();
                            }
                        } else {
                            if (lights.size() > 1) {
                                for (Light i : lights) {
                                    if (!i.isReserved()) {
                                        if (device.getState().isBeingUsed()) {
                                            step = new LightTurnOffEvent(device, this);
                                            device.reserve();
                                        } else {
                                            step = new LightTurnOffEvent(device, this);
                                            device.reserve();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    case 8 -> {
                        List<Window> windows = new ArrayList<>();
                        for (Floor i : this.room.getFloor().getHouse().getFloors()) {
                            for (Room r : i.getRooms()) {
                                windows.addAll(r.getWindows());
                            }
                        }
                        if (windows.isEmpty()) break;
                        Window window = windows.get(ThreadLocalRandom.current().nextInt(windows.size()));
                        if (window.getBlinds().areOpened()) step = new CloseBlindsEvent(window, this);
                        else step = new OpenBlindsEvent(window, this);
                    }
                    //personMove
                    default -> step = new PersonMoveEvent(this, this.room);
                }
            }
        }
        return step;
    }

    @Override
    public void doEvent(Event event) {
        switch (event.getEventType()) {
            case ENUM_DIY_REPAIR_EVENT -> doEvent((DIYRepairEvent) event);
            case ENUM_PERSON_MOVE_EVENT -> doEvent((PersonMoveEvent) event);
            case ENUM_WASHING_MACHINE_EVENT -> doEvent((WashingMachineEvent) event);
            case ENUM_FRIDGE_EVENT -> doEvent((FridgeEvent) event);
            case ENUM_BUY_EVENT -> doEvent((BuyEvent) event);
            case ENUM_SERVICE_REPAIR -> doEvent((ServiceRepairEvent) event);
            case ENUM_WATCH_TV -> doEvent((WatchTVEvent) event);
            case ENUM_LISTEN_TO_MUSIC -> doEvent((ListenToMusicEvent) event);
            case ENUM_GO_SKIING_EVENT -> doEvent((GoSkiingEvent) event);
            case ENUM_GO_BIKING_EVENT -> doEvent((GoBikingEvent) event);
            case ENUM_GO_RUNNING_EVENT -> doEvent((GoRunningEvent) event);
            case ENUM_OVEN_EVENT -> doEvent((OvenEvent) event);
            case ENUM_SLEEP_EVENT -> doEvent((SleepEvent) event);
            case ENUM_WORK_EVENT -> doEvent((WorkEvent) event);
        }
    }

    @Override
    public void doEvent(DIYRepairEvent event) {
        if (event.getDevice().getRoom() != this.getRoom()) {
            this.room.removePerson(this);
            this.room = event.getDevice().getRoom();
        }
        this.setActivity(Activity.ENUM_BUSY);
    }

    @Override
    public void doEvent(LightTurnOnEvent event) {
        if (event.getDevice().getRoom() != this.getRoom()) {
            this.room.removePerson(this);
            this.room = event.getDevice().getRoom();
        }
        this.setActivity(Activity.ENUM_BUSY);
        event.getDevice().getState().turnOnDevice(event.getDevice());
        event.getDevice().getState().useDevice(event.getDevice(), this);
    }

    @Override
    public void doEvent(LightTurnOffEvent event) {
        if (event.getDevice().getRoom() != this.getRoom()) {
            this.room.removePerson(this);
            this.room = event.getDevice().getRoom();
        }
        this.setActivity(Activity.ENUM_BUSY);
        event.getDevice().getState().turnOffDevice(event.getDevice());
    }

    @Override
    public void doEvent(OpenBlindsEvent event) {
        Room room = null;
        for (Floor f : this.room.getFloor().getHouse().getFloors()) {
            for (Room r : f.getRooms()) {
                if (r.getWindows().contains(event.getWindow())) room = r;
            }
        }
        if (room != this.getRoom()) {
            this.room.removePerson(this);
            this.room = room;
        }
        this.setActivity(Activity.ENUM_BUSY);
        event.getWindow().getBlinds().open();
    }

    @Override
    public void doEvent(CloseBlindsEvent event) {
        Room room = null;
        for (Floor f : this.room.getFloor().getHouse().getFloors()) {
            for (Room r : f.getRooms()) {
                if (r.getWindows().contains(event.getWindow())) room = r;
            }
        }
        if (room != this.getRoom()) {
            this.room.removePerson(this);
            this.room = room;
        }
        this.setActivity(Activity.ENUM_BUSY);
        event.getWindow().getBlinds().close();
    }

    @Override
    public void doEvent(BuyEvent event) {
        room.removeDevice(event.getDevice());
        room.getFloor().getHouse().removeDevice(event.getDevice());
        room.removePerson(this);
        room.getFloor().getHouse().removePerson(this);
        room.getFloor().getHouse().addDestroyedDevice(event.getDevice());
        this.setActivity(Activity.ENUM_BUSY);

    }

    @Override
    public void doEvent(ServiceRepairEvent event) {
        room.removeDevice(event.getDevice());
        room.getFloor().getHouse().removeDevice(event.getDevice());
        room.removePerson(this);
        room.getFloor().getHouse().removePerson(this);
        this.setActivity(Activity.ENUM_BUSY);
    }

    @Override
    public void doEvent(FridgeEvent event) {
        this.setActivity(Activity.ENUM_BUSY);
        event.getDevice().getState().useDevice(event.getDevice(), this);
    }

    @Override
    public void doEvent(ListenToMusicEvent event) {
        this.setActivity(Activity.ENUM_BUSY);
        event.getDevice().getState().turnOnDevice(event.getDevice());
        event.getDevice().getState().useDevice(event.getDevice(), this);
    }

    @Override
    public void doEvent(PersonMoveEvent event) {
        room.removePerson(this);
        event.getRoom().addPerson(this);
    }

    @Override
    public void doEvent(WashingMachineEvent event) {
        if (event.getDevice().getRoom() != this.getRoom()) {
            this.room.removePerson(this);
            this.room = event.getDevice().getRoom();
        }
        this.setActivity(Activity.ENUM_BUSY);
        event.getDevice().getState().turnOnDevice(event.getDevice());
        event.getDevice().getState().useDevice(event.getDevice(), this);
    }

    @Override
    public void doEvent(WatchTVEvent event) {
        if (event.getDevice().getRoom() != this.getRoom()) {
            this.room.removePerson(this);
            this.room = event.getDevice().getRoom();
        }
        this.setActivity(Activity.ENUM_BUSY);
        event.getDevice().getState().turnOnDevice(event.getDevice());
        event.getDevice().getState().useDevice(event.getDevice(), this);
    }

    @Override
    public void doEvent(GoSkiingEvent event) {
        room.removePerson(this);
        event.getSportItem().getRoom().removeItem(event.getSportItem());
        room.getFloor().getHouse().removeItem(event.getSportItem());
        room.getFloor().getHouse().removePerson(this);
        this.setActivity(Activity.ENUM_EXERCISE);
    }

    @Override
    public void doEvent(GoBikingEvent event) {
        room.removePerson(this);
        event.getSportItem().getRoom().removeItem(event.getSportItem());
        room.getFloor().getHouse().removeItem(event.getSportItem());
        room.getFloor().getHouse().removePerson(this);
        this.setActivity(Activity.ENUM_EXERCISE);
    }

    @Override
    public void doEvent(GoRunningEvent event) {
        room.removePerson(this);
        room.getFloor().getHouse().removePerson(this);
        this.setActivity(Activity.ENUM_EXERCISE);
    }

    @Override
    public void doEvent(SleepEvent event) {
        this.setActivity(Activity.ENUM_SLEEPING);
    }

    @Override
    public void doEvent(WorkEvent event) {
        this.getRoom().getFloor().getHouse().removePerson(this);
        this.getRoom().removePerson(this);
        this.setActivity(Activity.ENUM_WORK);
        this.setRoom(null);
    }

}
