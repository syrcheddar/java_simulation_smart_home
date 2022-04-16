package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.TelevisionSensor;
import cz.cvut.fel.omo.smarthome.devices.state.OffState;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class Television extends DeviceBase {
    public Television(double id, Room room) {
        super(id, room, DeviceType.ENUM_TELEVISION);
        this.deviceType = DeviceType.ENUM_TELEVISION;
        room.addDevice(this);
        this.setState(new OffState());
        sensor = new TelevisionSensor(IDHandler.getInstance().getNewID(), room, this);
    }

}
