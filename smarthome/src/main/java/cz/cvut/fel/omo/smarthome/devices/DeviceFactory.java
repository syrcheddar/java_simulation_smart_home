package cz.cvut.fel.omo.smarthome.devices;


import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

/**
 * Class for creating devices
 */
public class DeviceFactory {
    private static final IDHandler idHandler= IDHandler.getInstance();


    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static Television createTv(Room room) {
        return new Television(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static AirConditioner createAirConditioner(Room room) {
        return new AirConditioner(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static CDPlayer createCDPlayer(Room room) {
        return new CDPlayer(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static Fridge createFridge(Room room) {
        return new Fridge(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static Oven createOven(Room room) {
        return new Oven(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static WashingMachine createWashingMachine(Room room) {
        return new WashingMachine(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static Microwave createMicrowave(Room room) {
        return new Microwave(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static Light createLight(Room room) {
        return new Light(idHandler.getNewID(), room);
    }

    /**
     *
     * @param room Class Room, needed for proper functioning of device
     * @return Returns the device already paired with room. is still needed to be paired with House.
     */
    public static Thermometer createThermometer(Room room) {
        return new Thermometer(idHandler.getNewID(), room);
    }


}

