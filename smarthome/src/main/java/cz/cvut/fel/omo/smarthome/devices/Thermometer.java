package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.ThermometerSensor;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

import java.util.concurrent.ThreadLocalRandom;


public class Thermometer extends DeviceBase {
    public Thermometer(double id, Room room) {
        super(id, room, DeviceType.ENUM_THERMOMETER);
        this.deviceType = DeviceType.ENUM_THERMOMETER;
        sensor = new ThermometerSensor(IDHandler.getInstance().getNewID(), room, this);
    }

    public double getTemperature() {
        return ThreadLocalRandom.current().nextInt(18, 29);
    }
}
