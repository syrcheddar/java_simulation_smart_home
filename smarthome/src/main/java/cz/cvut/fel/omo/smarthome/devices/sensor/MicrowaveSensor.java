package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Microwave;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class MicrowaveSensor extends Sensor {
    public MicrowaveSensor(double id, Room room, Microwave device) {
        super(id, room, DeviceType.ENUM_AIR_CONDITIONING_SENSOR, device);
    }
}
