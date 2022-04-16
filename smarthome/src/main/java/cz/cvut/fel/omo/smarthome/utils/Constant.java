package cz.cvut.fel.omo.smarthome.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constant {

    public static final int DAY_LENGTH = 24 * 60;

    public static final int TIME_TICK = 10;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    public static final String DIRECTORY = "../smarthome/documents";

    public static final String HOUSE_CONFIG_REPORT = "House_Config_Report-"+ FORMATTER.format(LocalDateTime.now())+".txt";
    public static final String CONSUMPTION_REPORT = "Consumption_report-"+ FORMATTER.format(LocalDateTime.now())+".txt";
    public static final String EVENT_REPORT = "Event_report-"+ FORMATTER.format(LocalDateTime.now())+".txt";
    public static final String ACTIVITY_AND_USAGE_REPORT = "Activity_and_usage_report-"+ FORMATTER.format(LocalDateTime.now())+".txt";
    public static final int AC_DOWN_TEMP = 20;
    public static final int AC_UP_TEMP = 26;
    public static final int EVENT_DIY_REPAIR_TIME = 30;
    public static final int EVENT_BUY_TIME = 30;
    public static final int EVENT_SERVICE_REPAIR_TIME = 30;
    public static final int EVENT_DEVICE_BREAK_TIME = 0;
    public static final int EVENT_FRIDGE_USE_TIME = 1;
    public static final int EVENT_HOUSE_BOOM_TIME = 0;
    public static final int EVENT_PERSON_MOVE_TIME = 0;
    public static final int EVENT_PET_MOVE_TIME = 0;
    public static final int EVENT_WASHING_MACHINE = 30;
    public static final int EVENT_AC_TIME = 15;
    public static final int EVENT_LIGHT_TURN_OFF_TIME = 0;
    public static final int EVENT_LIGHT_TURN_ON_TIME = 0;
    public static final int EVENT_LISTEN_TO_MUSIC_TIME = 25;
    public static final int EVENT_WATCH_TV_TIME = 30;
    public static final int EVENT_GO_SKIING_EVENT_TIME = 2 * 60;
    public static final int EVENT_GO_BIKING_TIME = 60;
    public static final int EVENT_GO_RUNNING_TIME = 60;
    public static final int EVENT_OVEN_TIME = 30;
    public static final int EVENT_MICROWAVE_TIME = 3;

    public static final int PET_SLEEP_LENGTH = 2 * 60; //120 mins
    public static final int SPORT_LENGTH = 2 * 60; //120 mins
    public static final int SLEEP_LENGTH = 8 * 60; //480 mins
    public static final int WORK_LENGTH = 8 * 60; //480 mins
    public static final int BREAK_PROBABILITY = 1000;
    public static final int DEVICE_LIFETIME = 1000;
}
