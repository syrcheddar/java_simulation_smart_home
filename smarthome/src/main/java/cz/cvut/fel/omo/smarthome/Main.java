package cz.cvut.fel.omo.smarthome;

import cz.cvut.fel.omo.smarthome.building.House;
import cz.cvut.fel.omo.smarthome.eventHandler.MainEventHandler;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.ActivityAndUsageReport;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.ConsumptionReport;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.EventReport;
import cz.cvut.fel.omo.smarthome.eventHandler.reports.HouseConfigReport;
import cz.cvut.fel.omo.smarthome.utils.DataLoader;

public class Main {
    public static void main(String[] args) {
        final DataLoader dataLoader = new DataLoader();
        final House testHouse = dataLoader.getBuiltHouse();

        MainEventHandler mainEventHandler = MainEventHandler.getInstance(testHouse);

        mainEventHandler.begin(7);
        HouseConfigReport hr = new HouseConfigReport();
        ConsumptionReport cr = new ConsumptionReport();
        EventReport er = new EventReport();
        ActivityAndUsageReport ar = new ActivityAndUsageReport();
        testHouse.accept(hr);
        testHouse.accept(cr);
        mainEventHandler.Accept(er);
        mainEventHandler.Accept(ar);


    }
}
