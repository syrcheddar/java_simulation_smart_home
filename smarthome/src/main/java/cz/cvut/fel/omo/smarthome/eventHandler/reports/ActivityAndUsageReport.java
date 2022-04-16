package cz.cvut.fel.omo.smarthome.eventHandler.reports;

import cz.cvut.fel.omo.smarthome.beings.Person;
import cz.cvut.fel.omo.smarthome.beings.Pet;
import cz.cvut.fel.omo.smarthome.devices.Device;
import cz.cvut.fel.omo.smarthome.eventHandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventHandler.MainEventHandler;
import cz.cvut.fel.omo.smarthome.eventHandler.events.Event;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cz.cvut.fel.omo.smarthome.utils.Constant.ACTIVITY_AND_USAGE_REPORT;

public class ActivityAndUsageReport implements Visitor {
    private final StringBuilder stringBuilder = new StringBuilder();
    private final List<String> used = new ArrayList<>();
    private final List<String> usedItem = new ArrayList<>();
    private final List<String> petEvent = new ArrayList<>();
    private List<Event> allEndedEvents = MainEventHandler.getInstance().getAllEndedEvents();
    private List<Event> allActivities = MainEventHandler.getInstance().getAllEndedEvents();
    private List<Event> petActivities = MainEventHandler.getInstance().getAllEndedEvents();


    @Override
    public void visitEventHandler(EventHandler eventHandler) {
        this.allEndedEvents = this.getStartUsingApplianceEvents(eventHandler);
        this.allActivities = this.getNotStartUsingApplianceEvents(eventHandler);
        this.petActivities = this.getPetEvents(eventHandler);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Constant.DIRECTORY,ACTIVITY_AND_USAGE_REPORT)))) {

            this.stringBuilder.append("What devices persons used: \n\n");
            this.getPeople(eventHandler)
                    .forEach(p -> this.allEndedEvents
                            .forEach(e -> {
                                if (e.getDevice() != null) {
                                    if (this.isApplianceUsedByPerson(e, p)) {
                                        this.used.add(e.getDevice().getID() + " " + p.getID());

                                        this.stringBuilder
                                                .append(p.getName())
                                                .append(" used ");

                                        e.getDevice().accept(this);

                                        this.stringBuilder
                                                .append(this.getUsageCount(e))
                                                .append(" times. \r\n");
                                    }
                                }
                            })
                    );
            this.stringBuilder.append("\n\n\n").append("Person events: \n\n");
            this.getPeople(eventHandler)
                    .forEach(p -> this.allActivities
                            .forEach(e -> {
                                if (e.getPerson() != null) {
                                    if (this.isItemUsedByPerson(e, p)) {
                                        this.usedItem.add(e.getEventType() + " " + p.getID());

                                        this.stringBuilder
                                                .append(p.getName())
                                                .append(" did event: ")
                                                .append(e.getEventType())
                                                .append(" ");


                                        this.stringBuilder
                                                .append(this.getEventCount(e))
                                                .append(" times. \r\n");
                                    }
                                }
                            }));

            this.stringBuilder.append("\n\n\n").append("Pet Events: \n\n");
            this.getPet(eventHandler)
                    .forEach(p -> this.petActivities
                            .forEach(e -> {
                                if (e.getPet() != null) {
                                    if (e.getDevice() == null) {
                                        if (this.isItemUsedByPet(e, p)) {
                                            this.petEvent.add(e.getEventType() + " " + p.getID());

                                            this.stringBuilder
                                                    .append(p.getName())
                                                    .append(" did event: ")
                                                    .append(e.getEventType())
                                                    .append(" ");


                                            this.stringBuilder
                                                    .append(this.getPetEventCount(e))
                                                    .append(" times. \r\n");
                                        }
                                    }
                                }
                            }));
            String output = this.stringBuilder.toString();
            bw.write(output);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void visitDevice(Device device) {
        this.stringBuilder
                .append("Device: ")
                .append(device.getType())
                .append(", with ID = ")
                .append(device.getID())
                .append(" ... ");
    }

    private List<Person> getPeople(EventHandler eventHandler) {
        return eventHandler.getHouse().getPeople();
    }

    private List<Pet> getPet(EventHandler eventHandler) {
        return eventHandler.getHouse().getPets();
    }

    private List<Event> getStartUsingApplianceEvents(EventHandler eventHandler) {
        return eventHandler
                .getAllEndedEvents()
                .stream()
                .filter(e -> e.getDevice() != null && e.getPerson() != null)
                .collect(Collectors.toList());
    }

    public long getUsageCount(Event event) {
        return this.allEndedEvents
                .stream()
                .filter(e -> e.getPerson().getName()
                        .equals(event.getPerson().getName())
                        &&
                        event.getDevice().equals(e.getDevice()))
                .count();
    }

    private List<Event> getNotStartUsingApplianceEvents(EventHandler eventHandler) {
        return eventHandler
                .getAllEndedEvents()
                .stream()
                .filter(e -> e.getPerson() != null)
                .collect(Collectors.toList());
    }

    private List<Event> getPetEvents(EventHandler eventHandler) {
        return eventHandler
                .getAllEndedEvents()
                .stream()
                .filter(e -> e.getDevice() == null && e.getPet() != null)
                .collect(Collectors.toList());
    }

    public long getEventCount(Event event) {
        return this.allActivities
                .stream()
                .filter(e -> e.getPerson().getName()
                        .equals(event.getPerson().getName())
                        &&
                        event.getEventType().equals(e.getEventType()))
                .count();
    }

    public long getPetEventCount(Event event) {
        return this.petActivities
                .stream()
                .filter(e -> e.getPet().getName()
                        .equals(event.getPet().getName())
                        &&
                        event.getEventType().equals(e.getEventType()))
                .count();
    }

    private boolean isApplianceUsedByPerson(Event event, Person person) {
        return event.getPerson().getName().equals(person.getName()) && !this.used.contains(event.getDevice().getID() + " " + person.getID());
    }

    private boolean isItemUsedByPerson(Event event, Person person) {
        return event.getPerson().getName().equals(person.getName()) && !this.usedItem.contains(event.getEventType() + " " + person.getID());
    }

    private boolean isItemUsedByPet(Event event, Pet pet) {
        return event.getPet().getName().equals(pet.getName()) && !this.petEvent.contains(event.getEventType() + " " + pet.getID());
    }

}
