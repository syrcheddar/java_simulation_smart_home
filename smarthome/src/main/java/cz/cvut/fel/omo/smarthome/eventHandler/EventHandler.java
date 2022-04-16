package cz.cvut.fel.omo.smarthome.eventHandler;

import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.eventHandler.events.Event;
import cz.cvut.fel.omo.smarthome.eventHandler.events.FinishEvent;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

import java.util.ArrayList;
import java.util.List;

public interface EventHandler {

    void observe(Observer observer);

    void stopObserve(Observer observer);

    List<Event> getAllEndedEvents();

    void Accept(Visitor visitor);

    House getHouse();

    void addEvent(Event event);

    void addFinishEvent(FinishEvent event);

    int getTime();
}
