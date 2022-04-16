package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.WashingMachineSensor;
import cz.cvut.fel.omo.smarthome.devices.state.OffState;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class WashingMachine extends DeviceBase {
    public WashingMachine(double id, Room room) {
        super(id, room, DeviceType.ENUM_WASHING_MACHINE);
        this.deviceType = DeviceType.ENUM_WASHING_MACHINE;
        room.addDevice(this);
        this.setState(new OffState());
        sensor = new WashingMachineSensor(IDHandler.getInstance().getNewID(), room, this);
    }


}
