package cz.cvut.fel.omo.smarthome.devices.state;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;

public class IdleState implements DeviceState {
    @Override
    public boolean isBeingUsed() {
        return false;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public boolean isIdle() {
        return true;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public void useDevice(Device device, Person person) {
        device.setState(new ActiveState());
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
        //nothing happens -> its on
    }

    @Override
    public void repairDevice(Device device, Person human) {
        //nothing happens -> its not broken
    }

    @Override
    public void stopUsingDevice(Device device, Person human) {
        //nothing happens
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
