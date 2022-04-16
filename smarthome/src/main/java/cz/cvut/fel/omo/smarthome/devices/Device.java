package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.Sensor;
import cz.cvut.fel.omo.smarthome.devices.state.DeviceState;
import cz.cvut.fel.omo.smarthome.devices.utils.Documentation;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

public interface Device {

    double getID();

    DeviceType getType();

    Room getRoom();

    void setRoom(Room room);

    DeviceState getState();

    void setState(DeviceState state);

    Documentation getDocumentation();

    double getLifeTime();
    void setLifeTime(double lifeTime);

    Sensor getSensor();

    //void setRoom(Room room);
    boolean tryBreak();

    void accept(Visitor visitor);

    boolean isDeviceBroken();

    boolean isReserved();

    void reserve();
    void unreserve();

    void cancelReservation();

}


