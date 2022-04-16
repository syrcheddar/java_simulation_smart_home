package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Fridge;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class FridgeSensor extends Sensor {

    public FridgeSensor(double id, Room room, Fridge device) {
        super(id, room, DeviceType.ENUM_FRIDGE_SENSOR, device);
    }
}
