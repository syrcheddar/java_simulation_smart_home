package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Thermometer;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class ThermometerSensor extends Sensor {
    public ThermometerSensor(double id, Room room, Thermometer device) {
        super(id, room, DeviceType.ENUM_AIR_CONDITIONING_SENSOR, device);
    }
}
