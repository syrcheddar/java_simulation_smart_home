package cz.cvut.fel.omo.smarthome.utils;

public class IDHandler {

    private static IDHandler instance = null;

    private static double lastID;

    private IDHandler(){
        lastID=-1;
    }

    public static IDHandler getInstance() {
        if (instance == null) {
            instance = new IDHandler();
        }
        return instance;
    }

    public double getNewID() {
        lastID++;
        return lastID;
    }
}
