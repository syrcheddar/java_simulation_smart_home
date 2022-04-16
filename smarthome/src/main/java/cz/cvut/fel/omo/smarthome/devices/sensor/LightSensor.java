package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Light;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class LightSensor extends Sensor {
    public LightSensor(double id, Room room, Light device) {
        super(id, room, DeviceType.ENUM_LIGHT_SENSOR, device);
    }

}
