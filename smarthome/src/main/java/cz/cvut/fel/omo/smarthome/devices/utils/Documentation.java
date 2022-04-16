package cz.cvut.fel.omo.smarthome.devices.utils;

import cz.cvut.fel.omo.smarthome.enums.DeviceType;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

public class Documentation {
    private final String documentation;
    private final PowerConsumption powerConsumption;
    private final WaterConsumption waterConsumption;
    private final DeviceType deviceType;

    public Documentation(DeviceType deviceType) {
        this.deviceType = deviceType;
        String documentation = "Unknown Documentation";
        PowerConsumption powerConsumption = new PowerConsumption(-1, -1);
        WaterConsumption waterConsumption = new WaterConsumption(-1, -1);
        switch (deviceType) {
            case ENUM_AIR_CONDITIONER -> {
                documentation = "Air conditioner documentation";
                powerConsumption = new PowerConsumption(0.1, 3.5);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_CD_PLAYER -> {
                documentation = "CD Player documentation documentation";
                powerConsumption = new PowerConsumption(0, 0.1);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_FRIDGE -> {
                documentation = "Fridge documentation documentation";
                powerConsumption = new PowerConsumption(0.1, 0.1);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_MICROWAVE -> {
                documentation = "Microwave documentation";
                powerConsumption = new PowerConsumption(0.1, 1.5);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_OVEN -> {
                documentation = "Oven documentation";
                powerConsumption = new PowerConsumption(0.1, 2.3);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_LIGHT -> {
                documentation = "Light documentation";
                powerConsumption = new PowerConsumption(0, 0.5);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_TELEVISION -> {
                documentation = "Television documentation";
                powerConsumption = new PowerConsumption(0.1, 0.5);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_WASHING_MACHINE -> {
                documentation = "Washing machine documentation";
                powerConsumption = new PowerConsumption(0.1, 5);
                waterConsumption = new WaterConsumption(0, 71);
            }
            case ENUM_AIR_CONDITIONING_SENSOR, ENUM_FRIDGE_SENSOR, ENUM_LIGHT_SENSOR, ENUM_OVEN_SENSOR, ENUM_TELEVISION_SENSOR, ENUM_WASHING_MACHINE_SENSOR -> {
                documentation = "Sensor of activity documentation";
                powerConsumption = new PowerConsumption(0.01, 0.01);
                waterConsumption = new WaterConsumption(0, 0);
            }
            case ENUM_THERMOMETER -> {
                documentation = "Thermometer documentation";
                powerConsumption = new PowerConsumption(0.01, 0.01);
                waterConsumption = new WaterConsumption(0, 0);
            }
        }
        this.documentation = documentation;
        this.powerConsumption = powerConsumption;
        this.waterConsumption = waterConsumption;
    }

    public String getDocumentation() {
        return documentation;
    }

    public PowerConsumption getPowerConsumption() {
        return powerConsumption;
    }

    public WaterConsumption getWaterConsumption() {
        return waterConsumption;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void accept(Visitor visitor) {
        visitor.visitDocumentation(this);
    }
}
