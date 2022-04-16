package cz.cvut.fel.omo.smarthome.eventHandler.events;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.items.SportItem;

public class FinishEvent extends Event {
    private final Event event;
    private SportItem item = null;
    private Room roomToMove = null;

    public FinishEvent(Event event) {
        super(event.device, event.person, event.time, event.eventType);
        this.event = event;
    }

    public FinishEvent(GoBikingEvent event) {
        super(event.device, event.person, event.time, event.eventType);
        item = event.getSportItem();
        this.event = event;
    }

    public FinishEvent(GoSkiingEvent event) {
        super(event.device, event.person, event.time, event.eventType);
        item = event.getSportItem();
        this.event = event;
    }

    public FinishEvent(PetMoveEvent event) {
        super(event.device, event.person, event.time, event.eventType);
        roomToMove = event.getRoom();
        this.event = event;
    }

    public FinishEvent(PersonMoveEvent event) {
        super(event.device, event.person, event.time, event.eventType);
        roomToMove = event.getRoom();
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
