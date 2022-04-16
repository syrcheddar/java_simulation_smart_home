package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Oven;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class OvenSensor extends Sensor {
    public OvenSensor(double id, Room room, Oven device) {
        super(id, room, DeviceType.ENUM_OVEN_SENSOR, device);
    }
}
