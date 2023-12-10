package pro.kensait.java.timeofday;

/*
 * TimeManagerIFをimplementsしたモック
 */
public class MockTimeManager implements TimeManagerIF {
    // 時間帯
    private TimeOfDay mockTimeOfDay;

    // コンストラクタ
    public MockTimeManager(TimeOfDay mockTimeOfDay) {
        this.mockTimeOfDay = mockTimeOfDay;
    }

    // モックとしての振る舞い
    @Override
    public TimeOfDay getCurrent() {
        return mockTimeOfDay;
    }
}