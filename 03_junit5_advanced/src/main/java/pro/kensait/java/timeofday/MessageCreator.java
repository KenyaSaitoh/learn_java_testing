package pro.kensait.java.timeofday;

public class MessageCreator {
    private TimeManager timeService;

    public String getMessage_1() {
        TimeOfDay timeOfDay = timeService.getCurrent();
        String message = "現在は" + timeOfDay + "です。";
        return message;
    }

    public String getMessage_2() {
        TimeManager timeService = new TimeManager();
        TimeOfDay timeOfDay = timeService.getCurrent();
        String message = "現在は" + timeOfDay + "です。";
        return message;
    }

    public String getMessage_3() {
        TimeOfDay timeOfDay = TimeManager.getStaticCurrent();
        String message = "現在は" + timeOfDay + "です。";
        return message;
    }
}