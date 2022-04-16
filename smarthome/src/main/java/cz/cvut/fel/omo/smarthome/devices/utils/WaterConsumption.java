package cz.cvut.fel.omo.smarthome.devices.utils;

import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

public class WaterConsumption {
    private final int idle;
    private final int active;
    private double totalWaterConsumption = 0;

    public WaterConsumption(int idle, int active) {
        this.idle = idle;
        this.active = active;
    }

    public int getIdle() {
        return this.idle;
    }

    public int getActive() {
        return this.active;
    }

    public void accept(Visitor visitor) {
        visitor.visitWaterConsumption(this);
    }

    public void addTotalWaterConsumption(double consumption) {
        totalWaterConsumption += consumption;
    }

    public double getTotalWaterConsumption() {
        return totalWaterConsumption;
    }
}
