package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.AirConditioner;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class AirConditioningSensor extends Sensor {
    public AirConditioningSensor(double id, Room room, AirConditioner device) {
        super(id, room, DeviceType.ENUM_AIR_CONDITIONING_SENSOR, device);
    }
}
