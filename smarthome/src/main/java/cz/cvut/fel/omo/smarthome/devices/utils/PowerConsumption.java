package cz.cvut.fel.omo.smarthome.devices.utils;

import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

public class PowerConsumption {
    private final double idle;
    private final double active;
    private double totalPowerConsumption = 0;

    public PowerConsumption(double idle, double active) {
        this.idle = idle;
        this.active = active;
    }

    public double getIdle() {
        return this.idle;
    }

    public double getActive() {
        return this.active;
    }

    public void accept(Visitor visitor) {
        visitor.visitPowerConsumption(this);
    }

    public void addTotalPowerConsumption(double consumption) {
        totalPowerConsumption += consumption;
    }

    public double getTotalPowerConsumption() {
        return totalPowerConsumption;
    }
}
