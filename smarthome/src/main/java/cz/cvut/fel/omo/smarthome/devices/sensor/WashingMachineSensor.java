package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.WashingMachine;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;

public class WashingMachineSensor extends Sensor {

    public WashingMachineSensor(double id, Room room, WashingMachine device) {
        super(id, room, DeviceType.ENUM_WASHING_MACHINE_SENSOR, device);
    }

}
