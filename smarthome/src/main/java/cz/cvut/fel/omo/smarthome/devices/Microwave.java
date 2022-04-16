package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.MicrowaveSensor;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class Microwave extends DeviceBase {
    public Microwave(double id, Room room) {
        super(id, room, DeviceType.ENUM_MICROWAVE);
        this.deviceType = DeviceType.ENUM_MICROWAVE;
        room.addDevice(this);
        sensor = new MicrowaveSensor(IDHandler.getInstance().getNewID(), room, this);
    }
}
