package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.DeviceBase;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class TelevisionSensor extends Sensor {
    public TelevisionSensor(double id, Room room, DeviceBase device) {
        super(id, room, DeviceType.ENUM_TELEVISION_SENSOR, device);
    }
}
