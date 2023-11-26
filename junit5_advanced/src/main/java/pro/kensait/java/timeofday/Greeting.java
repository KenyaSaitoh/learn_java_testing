package pro.kensait.java.timeofday;

public class Greeting {
    private TimeManager timeService;

    public String getMessage_1() {
        TimeOfDay timeOfDay = timeService.getCurrent();
        String message = getGreeting(timeOfDay);
        return message;
    }

    public String getMessage_2() {
        TimeManager timeService = new TimeManager();
        TimeOfDay timeOfDay = timeService.getCurrent();
        String message = getGreeting(timeOfDay);
        return message;
    }

    public String getMessage_3() {
        TimeOfDay timeOfDay = TimeManager.getStaticCurrent();
        String message = getGreeting(timeOfDay);
        return message;
    }

    private String getGreeting(TimeOfDay timeOfDay) {
        return switch(timeOfDay) {
        case MORNING -> "おはよう";
        case AFTERNOON -> "こんにちは";
        case EVENING -> "こんばんは";
        };
    }
}