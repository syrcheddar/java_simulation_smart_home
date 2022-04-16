package cz.cvut.fel.omo.smarthome.eventHandler.reports;

import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import static cz.cvut.fel.omo.smarthome.utils.Constant.CONSUMPTION_REPORT;

public class ConsumptionReport implements Visitor {
    private final StringBuilder stringBuilder = new StringBuilder();
    private final DecimalFormat numberFormat = new DecimalFormat("#.##");
    private double totalElectricityCost = 0;
    private double totalWaterCost = 0;

    @Override
    public void visitHouse(House house) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Constant.DIRECTORY,CONSUMPTION_REPORT)))) {

            this.stringBuilder
                    .append("Consumption Report \r\n\n");
            house.getDevices().forEach(device -> device.accept(this));
            stringBuilder
                    .append("Destroyed devices: \r\n\n");
            house.getDestroyedDevices().forEach(device -> device.accept(this));
            stringBuilder
                    .append("Total electricity cost: ")
                    .append(numberFormat.format(totalElectricityCost))
                    .append(" CZK")
                    .append("\r\n")
                    .append("Total water cost: ")
                    .append(numberFormat.format(totalWaterCost))
                    .append(" CZK")
                    .append("\r\n");


            String output = this.stringBuilder.toString();
            bw.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitDevice(Device device) {
        this.stringBuilder
                .append("Device: ")
                .append(device.toString())
                .append("\r\n\t")
                .append("consumed: ")
                .append(numberFormat.format(device.getDocumentation().getPowerConsumption().getTotalPowerConsumption()))
                .append(" kWh")
                .append(" and: ")
                .append(numberFormat.format(device.getDocumentation().getWaterConsumption().getTotalWaterConsumption()))
                .append(" l of water")
                .append("\r\n\n");
        totalElectricityCost += 1.1 * device.getDocumentation().getPowerConsumption().getTotalPowerConsumption();
        totalWaterCost += 0.1 * device.getDocumentation().getWaterConsumption().getTotalWaterConsumption();
    }
}
