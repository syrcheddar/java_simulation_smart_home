package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.AirConditioningSensor;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class AirConditioner extends DeviceBase {
    public AirConditioner(double id, Room room) {
        super(id, room, DeviceType.ENUM_AIR_CONDITIONER);
        this.deviceType = DeviceType.ENUM_AIR_CONDITIONER;
        room.addDevice(this);
        sensor = new AirConditioningSensor(IDHandler.getInstance().getNewID(), room, this);
    }

}
