package cz.cvut.fel.omo.smarthome.eventHandler;

import cz.cvut.fel.omo.smarthome.eventHandler.events.*;

public interface Observer {
    default void doEvent(Event event) {
    }

    default void doEvent(OpenBlindsEvent event) {
    }

    default void doEvent(CloseBlindsEvent event) {
    }

    default void doEvent(DIYRepairEvent event) {
    }

    default void doEvent(BuyEvent event) {
    }

    default void doEvent(AirConditionerEvent event) {
    }

    default void doEvent(FridgeEvent event) {
    }

    default void doEvent(HouseBoomEvent event) {
    }

    default void doEvent(LightTurnOffEvent event) {
    }

    default void doEvent(LightTurnOnEvent event) {
    }

    default void doEvent(ListenToMusicEvent event) {
    }

    default void doEvent(PersonMoveEvent event) {
    }

    default void doEvent(PetMoveEvent event) {
    }

    default void doEvent(PetSleepEvent event) {
    }

    default void doEvent(ServiceRepairEvent event) {
    }

    default void doEvent(WashingMachineEvent event) {
    }

    default void doEvent(WatchTVEvent event) {
    }

    default void doEvent(GoSkiingEvent event) {
    }

    default void doEvent(GoBikingEvent event) {
    }

    default void doEvent(GoRunningEvent event) {
    }

    default void doEvent(OvenEvent event) {
    }

    default void doEvent(SleepEvent event) {
    }

    default void doEvent(WorkEvent event) {
    }

    default void update() {
    }
}
