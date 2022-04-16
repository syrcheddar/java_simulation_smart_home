package cz.cvut.fel.omo.smarthome.devices.state;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;

public class OffState implements DeviceState {

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
        return false;
    }

    @Override
    public boolean isOff() {
        return true;
    }

    @Override
    public void useDevice(Device device, Person person) {
        //nothing happens -> its off
    }

    @Override
    public void turnOffDevice(Device device) {
        //nothing happens -> its off
    }

    @Override
    public void turnOnDevice(Device device) {
        device.setState(new IdleState());
    }

    @Override
    public void repairDevice(Device device, Person human) {
        //nothing happens -> its off
    }

    @Override
    public void stopUsingDevice(Device device, Person human) {
        //nothing happens -> its off
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
