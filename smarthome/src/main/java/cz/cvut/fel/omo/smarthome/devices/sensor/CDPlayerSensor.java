package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.CDPlayer;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class CDPlayerSensor extends Sensor {
    public CDPlayerSensor(double id, Room room, CDPlayer device) {
        super(id, room, DeviceType.ENUM_AIR_CONDITIONING_SENSOR, device);
    }
}
