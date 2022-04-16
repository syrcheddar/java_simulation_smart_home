package cz.cvut.fel.omo.smarthome.devices.state;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;

public class BrokenState implements DeviceState {

    @Override
    public boolean isBeingUsed() {
        return false;
    }

    @Override
    public boolean isBroken() {
        return true;
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
        //nothing happens -> its broken
    }

    @Override
    public void turnOffDevice(Device device) {
        //nothing happens -> its broken
    }

    @Override
    public void turnOnDevice(Device device) {
        //nothing happens -> its broken
    }

    @Override
    public void stopUsingDevice(Device device, Person human) {
        //nothing happens -> its broken
    }

    @Override
    public void repairDevice(Device device, Person human) {
        device.setState(new IdleState());
    }

    @Override
    public void buyNewDevice(Device device, Person human) {
        //human.doEvent(BuyEvent);
    }

    @Override
    public void takeDeviceToRepairService(Device device, Person human) {
        //human.doEvent(ServiceRepairEvent.AvailableEvents.get(1));
    }
}
