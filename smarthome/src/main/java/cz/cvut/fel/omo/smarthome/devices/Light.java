package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.LightSensor;
import cz.cvut.fel.omo.smarthome.devices.state.OffState;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class Light extends DeviceBase {
    public Light(double id, Room room) {
        super(id, room, DeviceType.ENUM_LIGHT);
        this.deviceType = DeviceType.ENUM_LIGHT;
        room.addDevice(this);
        this.setState(new OffState());
        sensor = new LightSensor(IDHandler.getInstance().getNewID(), room, this);
    }

}
