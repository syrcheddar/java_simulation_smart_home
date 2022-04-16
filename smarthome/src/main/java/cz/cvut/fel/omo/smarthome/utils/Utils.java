package cz.cvut.fel.omo.smarthome.utils;

import java.time.LocalDate;
import java.time.Period;

public class Utils {
    public static int getAgeFromDate(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        return Period.between(birthDate, now).getYears();
    }
}
