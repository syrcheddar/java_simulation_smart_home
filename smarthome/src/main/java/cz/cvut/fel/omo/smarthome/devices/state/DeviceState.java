package cz.cvut.fel.omo.smarthome.devices.state;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.devices.Device;

//todo toto by měl být State pattern
public interface DeviceState {
    //4 states

    boolean isBeingUsed(); //active state

    boolean isBroken(); //broken state

    boolean isIdle(); //idle state

    boolean isOff(); //off state


    //methods

    void useDevice(Device device, Person person);

    void turnOffDevice(Device device);

    void turnOnDevice(Device device);

    void repairDevice(Device device, Person human);

    void stopUsingDevice(Device device, Person human);

    void takeDeviceToRepairService(Device device, Person human);

    void buyNewDevice(Device device, Person human);


}
