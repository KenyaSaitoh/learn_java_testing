package pro.kensait.java.timeofday;

import java.time.LocalTime;

/*
 * 時間管理のためのビジネスロジック（モック対象）
 */
public class TimeManager implements TimeManagerIF {
    // 現在時刻から、朝、午後、夜のいずれかの時間帯なのかを判定して、返す
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

    // getCurrent()メソッドと同様の処理（スタティックメソッド）
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