package pro.kensait.java.timeofday;

import java.time.LocalTime;

public class TimeManager {
    public TimeOfDay getCurrent() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        if (6 <= hour && hour < 12) {
            return TimeOfDay.MORNING;
        } else if (12 <= hour && hour < 18) {
            return TimeOfDay.AFTERNOON;
        } else {
            return TimeOfDay.EVENING;
        }
    }

    public static TimeOfDay getStaticCurrent() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        if (6 <= hour && hour < 12) {
            return TimeOfDay.MORNING;
        } else if (12 <= hour && hour < 18) {
            return TimeOfDay.AFTERNOON;
        } else {
            return TimeOfDay.EVENING;
        }
    }
}