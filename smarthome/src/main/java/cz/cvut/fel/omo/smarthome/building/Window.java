package cz.cvut.fel.omo.smarthome.building;

import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

public class Window {
    private final Blinds blinds;

    /**
     * Constructor for Window
     * @param blinds Class Blinds
     */
    public Window(Blinds blinds) {
        this.blinds = blinds;
    }

    public Blinds getBlinds() {
        return blinds;
    }

    public void accept(Visitor visitor) {
        visitor.visitWindow(this);
    }
}
