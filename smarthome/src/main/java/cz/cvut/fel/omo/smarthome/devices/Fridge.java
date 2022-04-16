package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.FridgeSensor;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;

public class Fridge extends DeviceBase {
    public Fridge(double id, Room room) {
        super(id, room, DeviceType.ENUM_FRIDGE);
        this.deviceType = DeviceType.ENUM_FRIDGE;
        room.addDevice(this);
        sensor = new FridgeSensor(IDHandler.getInstance().getNewID(), room, this);
    }

    @Override
    public void accept(Visitor visitor) {
        super.accept(visitor);
    }
}
