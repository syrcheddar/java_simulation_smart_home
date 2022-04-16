package cz.cvut.fel.omo.smarthome.devices.state;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;

public class ActiveState implements DeviceState {

    @Override
    public boolean isBeingUsed() {
        return true;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public boolean isIdle() {
        return false;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public void useDevice(Device device, Person person) {
        if (device.tryBreak()) {
            device.setState(new BrokenState());
        }
    }

    @Override
    public void turnOffDevice(Device device) {
        device.setState(new OffState());
    }

    @Override
    public void turnOnDevice(Device device) {
        //nothing happens -> we are using it = it must be on
    }

    @Override
    public void repairDevice(Device device, Person human) {
        //nothing happens -> we are using it = it can´t be broken
    }

    @Override
    public void stopUsingDevice(Device device, Person human) {
        device.setState(new IdleState());
    }

    @Override
    public void takeDeviceToRepairService(Device device, Person human) {
        //nothing happens
    }

    @Override
    public void buyNewDevice(Device device, Person human) {
        //nothing happens
    }
}
