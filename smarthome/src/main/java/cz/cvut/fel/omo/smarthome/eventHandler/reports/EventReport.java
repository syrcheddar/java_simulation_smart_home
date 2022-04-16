package cz.cvut.fel.omo.smarthome.eventHandler.reports;

import cz.cvut.fel.omo.smarthome.eventHandler.EventHandler;
import cz.cvut.fel.omo.smarthome.eventHandler.events.Event;
import cz.cvut.fel.omo.smarthome.eventHandler.events.GoBikingEvent;
import cz.cvut.fel.omo.smarthome.eventHandler.events.GoRunningEvent;
import cz.cvut.fel.omo.smarthome.eventHandler.events.GoSkiingEvent;
import cz.cvut.fel.omo.smarthome.utils.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static cz.cvut.fel.omo.smarthome.utils.Constant.EVENT_REPORT;

public class EventReport implements Visitor {
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void visitEventHandler(EventHandler eventHandler) {
        List<Event> allPastEvents = eventHandler.getAllEndedEvents();
        this.SaveByTypeSourceTarget(allPastEvents);
    }

    @Override
    public void visitEvent(Event event) {
        this.stringBuilder
                .append("\r\n Event ")
                .append("raise time = ")
                .append(event.getStartTime())
                .append(", type = ")
                .append(event.getEventType().toString());

        if (event.getPerson() != null) {
            this.stringBuilder.append(", source = ")
                    .append(event.getPerson().getName())
                    .append(" - ID: ")
                    .append(event.getPerson().getID());
        } else if (event.getPet() != null) {
            this.stringBuilder.append(", source = ")
                    .append(event.getPet().getName())
                    .append(" - ID: ")
                    .append(event.getPet().getID());
        } else {
            this.stringBuilder.append(", source = House");
        }
        if (event.getDevice() != null) {
            this.stringBuilder.append(", target = ")
                    .append(event.getDevice().getType())
                    .append(" - ID: ")
                    .append(event.getDevice().getID());
        } else if (event instanceof GoSkiingEvent) {
            GoSkiingEvent e = (GoSkiingEvent) event;
            this.stringBuilder.append(", target = ")
                    .append(e.getSportItem().getName())
                    .append(" - ID: ")
                    .append(e.getSportItem().getId());
        } else if (event instanceof GoBikingEvent) {
            GoBikingEvent e = (GoBikingEvent) event;
            this.stringBuilder.append(", target = ")
                    .append(e.getSportItem().getName())
                    .append(" - ID: ")
                    .append(e.getSportItem().getId());
        } else if (event instanceof GoRunningEvent) {
            this.stringBuilder.append(", target = legs");
        }
    }

    private void SaveByTypeSourceTarget(List<Event> allPastEvents) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Constant.DIRECTORY,EVENT_REPORT)))) {

            //TYPE
            this.stringBuilder
                    .append("Printing events sorted by their type: \r\n")
                    .append("************************************** \r\n");
            allPastEvents
                    .stream()
                    .sorted(Comparator.comparing(Event::getEventType))
                    .forEach(e -> e.accept(this));

            this.stringBuilder.append("\n\n\n");

            //SOURCE
            this.stringBuilder
                    .append("\r\nPrinting events sorted by its source\r\n")
                    .append("************************************** \r\n").append("\r\n\n");
            allPastEvents
                    .stream()
                    .filter(e -> e.getPerson() != null)
                    .sorted(Comparator.comparing(a -> a.getPerson().getID()))
                    .forEach(e -> e.accept(this));
            allPastEvents
                    .stream()
                    .filter(e -> e.getPet() != null)
                    .sorted(Comparator.comparing(a -> a.getPet().getID()))
                    .forEach(e -> e.accept(this));
            allPastEvents
                    .stream()
                    .filter(e -> e.getPerson() == null)
                    .filter(e -> e.getPet() == null)
                    .forEach(e -> e.accept(this));
            this.stringBuilder.append("\n\n\n");


            //TARGET
            this.stringBuilder
                    .append("\r\nPrinting events sorted by its target\r\n")
                    .append("************************************** \r\n").append("\r\n\n");
            allPastEvents
                    .stream()
                    .filter(e -> e.getDevice() != null)
                    .sorted(Comparator.comparing(a -> a.getDevice().getID()))
                    .forEach(e -> e.accept(this));

            List<GoSkiingEvent> allSkiEvents = new ArrayList<>();
            allPastEvents.forEach(e -> {
                if (e instanceof GoSkiingEvent)
                    allSkiEvents.add((GoSkiingEvent) e);
            });
            allSkiEvents
                    .stream()
                    .filter(e -> e.getSportItem() != null)
                    .sorted(Comparator.comparing(a -> a.getSportItem().getId()))
                    .forEach(e -> e.accept(this));

            List<GoBikingEvent> allBikeEvents = new ArrayList<>();
            allPastEvents.forEach(e -> {
                if (e instanceof GoBikingEvent)
                    allBikeEvents.add((GoBikingEvent) e);
            });
            allBikeEvents
                    .stream()
                    .filter(e -> e.getSportItem() != null)
                    .sorted(Comparator.comparing(a -> a.getSportItem().getId()))
                    .forEach(e -> e.accept(this));

            List<GoRunningEvent> allRunningEvents = new ArrayList<>();
            allPastEvents.forEach(e -> {
                if (e instanceof GoRunningEvent)
                    allRunningEvents.add((GoRunningEvent) e);
            });
            allRunningEvents
                    .forEach(e -> e.accept(this));


            this.stringBuilder.append("\r\n");
            String output = this.stringBuilder.toString();
            bw.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
