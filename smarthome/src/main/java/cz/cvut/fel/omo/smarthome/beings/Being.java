package cz.cvut.fel.omo.smarthome.beings;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.Activity;
import cz.cvut.fel.omo.smarthome.enums.Gender;
import cz.cvut.fel.omo.smarthome.eventHandler.Observer;
import cz.cvut.fel.omo.smarthome.utils.IDHandler;
import cz.cvut.fel.omo.smarthome.utils.Utils;

import java.time.LocalDate;

public abstract class Being implements Observer {
    protected final double id;
    protected final String name;
    protected final Gender gender;
    protected final LocalDate birthDate;
    protected Room room;
    protected Activity activity;

    protected Being(String name, Gender gender, LocalDate birthDate, Room room) {
        IDHandler idHandler = IDHandler.getInstance();
        this.id = idHandler.getNewID();
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.room = room;
        activity = Activity.ENUM_FREE;
    }

    public int getAge() {
        return Utils.getAgeFromDate(this.birthDate);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public double getID() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
