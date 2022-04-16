package cz.cvut.fel.omo.smarthome.devices.sensor;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.devices.DeviceBase;
import cz.cvut.fel.omo.smarthome.devices.utils.PowerConsumption;
import cz.cvut.fel.omo.smarthome.devices.utils.WaterConsumption;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.eventHandler.Observer;

public abstract class Sensor extends DeviceBase implements Observer {
    Device device;

    public Sensor(double id, Room room, DeviceType deviceType, Device device) {
        super(id, room, deviceType);
        this.device = device;
    }

    @Override
    public void update() {
        if (device.getState().isBeingUsed()) {
            PowerConsumption pw = device.getDocumentation().getPowerConsumption();
            pw.addTotalPowerConsumption(pw.getActive() / 6.0);
            WaterConsumption ww = device.getDocumentation().getWaterConsumption();
            ww.addTotalWaterConsumption(ww.getActive() / 6.0);
        } else if (device.getState().isIdle()) {
            PowerConsumption pw = device.getDocumentation().getPowerConsumption();
            pw.addTotalPowerConsumption(pw.getIdle() / 6.0);
            WaterConsumption ww = device.getDocumentation().getWaterConsumption();
            ww.addTotalWaterConsumption(ww.getIdle() / 6.0);
        }
    }
}
