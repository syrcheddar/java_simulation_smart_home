package cz.cvut.fel.omo.smarthome.eventHandler.reports;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.building.Floor;
import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.building.Window;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.devices.utils.Documentation;
import cz.cvut.fel.omo.smarthome.devices.utils.PowerConsumption;
import cz.cvut.fel.omo.smarthome.devices.utils.WaterConsumption;
import cz.cvut.fel.omo.smarthome.items.SportItem;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static cz.cvut.fel.omo.smarthome.utils.Constant.HOUSE_CONFIG_REPORT;

public class HouseConfigReport implements Visitor {
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void visitHouse(House house) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Constant.DIRECTORY,HOUSE_CONFIG_REPORT)))) {
            // House main info
            this.stringBuilder
                    .append("House: ")
                    .append(house.getHouseName())
                    .append("\r\n");
            house.getFloors().forEach(f -> {
                this.stringBuilder.append("\t");
                f.accept(this);
            });

            // People info
            this.stringBuilder
                    .append("People:")
                    .append("\r\n");
            house.getPeople().forEach(p -> {
                p.accept(this);
                this.stringBuilder.append("\r\n");
            });

            // Devices info
            this.stringBuilder
                    .append("Devices:")
                    .append("\r\n");
            house.getDevices().forEach(a -> {
                a.accept(this);
                this.stringBuilder.append("\r\n");
            });

            // Pets info
            this.stringBuilder
                    .append("Pets:")
                    .append("\r\n");
            house.getPets().forEach(p -> {
                p.accept(this);
                this.stringBuilder.append("\r\n");
            });

            // Items info
            this.stringBuilder
                    .append("Items:")
                    .append("\r\n");
            house.getItems().forEach(a -> {
                a.accept(this);
                this.stringBuilder.append("\r\n");
            });

            String output = this.stringBuilder.toString();
            bw.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitPerson(Person person) {
        this.stringBuilder
                .append("\t")
                .append("Person: ")
                .append(person.getName())
                .append(" ")
                .append(person.getSurName())
                .append("\r\n")
                .append("\t\t")
                .append("Age: ")
                .append(person.getAge())
                .append("\r\n")
                .append("\t\t")
                .append("Gender: ")
                .append(person.getGender())
                .append("\r\n")
                .append("\t\t")
                .append("Activity: ")
                .append(person.getActivity().toString());

        this.stringBuilder
                .append("\r\n")
                .append("\t\t")
                .append("Room: ");
        if (person.getRoom() == null) {
            this.stringBuilder.append("The person is not in house right now");
        } else {
            this.stringBuilder.append(person.getRoom().getRoomName());
        }

        this.stringBuilder.append("\r\n");
    }

    @Override
    public void visitFloor(Floor floor) {
        this.stringBuilder
                .append("Floor: ")
                .append(floor.getName())
                .append("\r\n");

        floor.getRooms().forEach(r -> {
            this.stringBuilder.append("\t\t");
            r.accept(this);
            this.stringBuilder.append("\r\n");
        });
    }

    @Override
    public void visitRoom(Room room) {

        this.stringBuilder
                .append("Room: ")
                .append(room.getRoomName())
                .append("\r\n");

        for (int i = 0; i < room.getWindows().size(); i++) {
            this.stringBuilder
                    .append("\t\t\t")
                    .append("Window: Number ")
                    .append(i + 1)
                    .append("\r\n");
            room.getWindows().get(i).accept(this);
            this.stringBuilder.append("\r\n");
        }
    }

    @Override
    public void visitWindow(Window window) {
        this.stringBuilder
                .append("\t\t\t\t")
                .append("Blinds: ");

        if (window.getBlinds().areOpened()) {
            this.stringBuilder.append("opened");
        } else {
            this.stringBuilder.append("closed");
        }
    }

    @Override
    public void visitItem(SportItem item) {
        this.stringBuilder
                .append("\t")
                .append("Item: ")
                .append(item.getName())
                .append("\r\n")
                .append("\t\t")
                .append("In room: ")
                .append(item.getRoom().getRoomName())
                .append("\r\n");
    }

    @Override
    public void visitDevice(Device device) {
        this.stringBuilder
                .append("\t")
                .append("Device: ")
                .append(device.getType())
                .append(", ")
                .append(device.getID())
                .append("\r\n")
                .append("\t\t")
                .append("Consumption: ");


        device.getDocumentation()
                .accept(this);
        this.stringBuilder.append("\r\n");
    }

    @Override
    public void visitPowerConsumption(PowerConsumption powerConsumption) {
        this.stringBuilder
                .append("\t")
                .append("Idle: ")
                .append(powerConsumption.getIdle())
                .append("\r\n")
                .append("\t\t\t\t\t\t")
                .append("Active: ")
                .append(powerConsumption.getActive());
    }

    @Override
    public void visitWaterConsumption(WaterConsumption waterConsumption) {
        this.stringBuilder
                .append("\t")
                .append("Idle: ")
                .append(waterConsumption.getIdle())
                .append("\r\n")
                .append("\t\t\t\t\t\t")
                .append("Active: ")
                .append(waterConsumption.getActive());
    }

    @Override
    public void visitPet(Pet pet) {
        this.stringBuilder
                .append("\t")
                .append("Pet: ")
                .append(pet.getName())
                .append("\r\n")
                .append("\t\t")
                .append("Age: ")
                .append(pet.getAge())
                .append(", breed: ")
                .append(pet.getAnimalType())
                .append(", gender: ")
                .append(pet.getGender())
                .append("\r\n");
    }

    @Override
    public void visitDocumentation(Documentation documentation) {
        this.stringBuilder
                .append("\t")
                .append("Documentation: ")
                .append(documentation.getDocumentation())
                .append("\r\n")
                .append("\t\t\t\t\t\t")
                .append("Power consumption: ");
        documentation.getPowerConsumption().accept(this);
        this.stringBuilder.append("\r\n")
                .append("\t\t\t\t\t\t")
                .append("Water consumption: ");
        documentation.getWaterConsumption().accept(this);
        this.stringBuilder.append("\r\n");
    }
}
