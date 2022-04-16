package cz.cvut.fel.omo.smarthome.building;

public class Blinds {
    private boolean areOpened;

    /**
     * Default constructor
     */
    public Blinds() {
        this.areOpened = true;
    }

    public boolean areOpened() {
        return this.areOpened;
    }

    @Override
    public String toString() {
        if (areOpened) return "Opened blinds.";
        return "Closed blinds.";
    }

    public void open() {
        this.areOpened = true;
    }

    public void close() {
        this.areOpened = false;
    }

}
