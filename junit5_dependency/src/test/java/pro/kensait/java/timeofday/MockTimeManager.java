package pro.kensait.java.timeofday;

public class MockTimeManager implements TimeManagerIF {

    private TimeOfDay mockTimeOfDay;

    public MockTimeManager(TimeOfDay mockTimeOfDay) {
        this.mockTimeOfDay = mockTimeOfDay;
    }

    @Override
    public TimeOfDay getCurrent() {
        return mockTimeOfDay;
    }
}