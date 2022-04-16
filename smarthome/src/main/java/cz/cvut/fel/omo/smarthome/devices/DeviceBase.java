package cz.cvut.fel.omo.smarthome.devices;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.devices.sensor.Sensor;
import cz.cvut.fel.omo.smarthome.devices.state.DeviceState;
import cz.cvut.fel.omo.smarthome.devices.state.IdleState;
import cz.cvut.fel.omo.smarthome.devices.utils.Documentation;
import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.util.Random;

public abstract class DeviceBase implements Device {
    private final double id;
    protected DeviceType deviceType;
    protected DeviceState state;
    protected Documentation documentation;
    protected Sensor sensor;
    private Room room;
    private boolean reserved = false;
    private double lifeTime;

    public DeviceBase(double id, Room room, DeviceType deviceType) {
        this.id = id;
        this.room = room;
        documentation = new Documentation(deviceType);
        this.lifeTime = Constant.DEVICE_LIFETIME;
        state = new IdleState();
    }

    @Override
    public double getLifeTime() {
        return lifeTime;
    }

    @Override
    public void setLifeTime(double lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDevice(this);
    }

    @Override
    public double getID() {
        return this.id;
    }

    @Override
    public DeviceType getType() {
        return this.deviceType;
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public DeviceState getState() {
        return this.state;
    }

    @Override
    public void setState(DeviceState state) {
        this.state = state;
    }

    @Override
    public Documentation getDocumentation() {
        return documentation;
    }

    @Override
    public boolean tryBreak() {
        Random random = new Random();
        int number = random.nextInt((int) lifeTime + 1);
        return number == 1;
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }

    @Override
    public void reserve() {
        this.reserved = true;
    }
    @Override
    public void unreserve() {
        this.reserved = false;
    }

    @Override
    public void cancelReservation() {
        this.reserved = false;
    }


    @Override
    public String toString() {
        return "Device{" +
                "deviceName='" + deviceType + '\'' +
                ", id =" + getID() +
                ", room =" + getRoom() +
                ", broken=" + state.isBroken() +
                '}';
    }

    public Sensor getSensor() {
        return sensor;
    }


    @Override
    public boolean isDeviceBroken() {
        return this.getState().isBroken();
    }

}
