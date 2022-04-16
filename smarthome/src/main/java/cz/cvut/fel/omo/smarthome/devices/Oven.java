package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.OvenSensor;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class Oven extends DeviceBase {
    public Oven(double id, Room room) {
        super(id, room, DeviceType.ENUM_OVEN);
        this.deviceType = DeviceType.ENUM_OVEN;
        room.addDevice(this);
        sensor = new OvenSensor(IDHandler.getInstance().getNewID(), room, this);
    }
}
