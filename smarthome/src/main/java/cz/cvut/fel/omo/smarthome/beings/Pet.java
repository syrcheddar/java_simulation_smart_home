package cz.cvut.fel.omo.smarthome.beings;

import cz.cvut.fel.omo.smarthome.building.Room;
import cz.cvut.fel.omo.smarthome.enums.Activity;
import cz.cvut.fel.omo.smarthome.enums.AnimalType;
import cz.cvut.fel.omo.smarthome.enums.Gender;
import cz.cvut.fel.omo.smarthome.eventHandler.events.Event;
import cz.cvut.fel.omo.smarthome.eventHandler.events.PetMoveEvent;
import cz.cvut.fel.omo.smarthome.eventHandler.events.PetSleepEvent;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.Visitor;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Pet extends Being {
    private final AnimalType animalType;

    /**
     * Constructor for pet.
     * @param name String name of pet.
     * @param birthDate LocalDate of his birthdate.
     * @param animalType Enum type of animal.
     * @param gender Enum gender.
     * @param room Instance of room in which will the pet spawn.
     */
    public Pet(String name, LocalDate birthDate, AnimalType animalType, Gender gender, Room room) {
        super(name, gender, birthDate, room);
        this.animalType = animalType;
        room.addPet(this);
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void accept(Visitor visitor) {
        visitor.visitPet(this);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + this.getName() + '\'' +
                ", id =" + this.getID() +
                ", room =" + this.getRoom() +
                '}';
    }

    @Override
    public void doEvent(Event event) {
        switch (event.getEventType()) {
            case ENUM_PET_MOVE_EVENT -> {
                if (event instanceof PetMoveEvent) {
                    doEvent((PetMoveEvent) event);
                }
            }
            case ENUM_PET_SLEEP_EVENT -> {
                if (event instanceof PetSleepEvent) {
                    doEvent((PetSleepEvent) event);
                }
            }
        }
    }

    @Override
    public void doEvent(PetMoveEvent event) {
        room.removePet(this);
        event.getRoom().addPet(this);
        this.setActivity(Activity.ENUM_FREE);
    }

    @Override
    public void doEvent(PetSleepEvent event) {
        this.setActivity(Activity.ENUM_SLEEPING);
    }

    public Event getNextPetStep() {
        Event step = new PetMoveEvent(this, this.room);
        boolean activityType = ThreadLocalRandom.current().nextBoolean();
        if (activityType) {
            int activity = ThreadLocalRandom.current().nextInt(0, 2);
            switch (activity) {
                //petMove
                case 0 -> step = new PetMoveEvent(this, this.room);

                //petSleep
                case 1 -> step = new PetSleepEvent(this, this.room);

                default -> step = new PetMoveEvent(this, this.room);
            }
        }
        return step;
    }


}
