package pro.kensait.java.timeofday;

/*
 * 挨拶メッセージを生成するビジネスロジック（テスト対象）
 */
public class Greeting {
    // 時間管理のためのビジネスロジック（依存性注入対象）
    private TimeManagerIF timeManager;

    // コンストラクタ
    public Greeting(TimeManagerIF timeManager) {
        this.timeManager = timeManager;
    }

    public Greeting() {
    }

    // 現在時刻から挨拶のメッセージを取得し返す（依存性注入で取得）
    public String getMessage_1() {
        TimeOfDay timeOfDay = timeManager.getCurrent();
        String message = getGreeting(timeOfDay);
        return message;
    }

    // 現在時刻から挨拶のメッセージを取得し返す（new演算子で生成）
    public String getMessage_2() {
        TimeManager timeManager = new TimeManager();
        TimeOfDay timeOfDay = timeManager.getCurrent();
        String message = getGreeting(timeOfDay);
        return message;
    }

    // 現在時刻から挨拶のメッセージを取得し返す（スタティックメソッド呼び出し）
    public String getMessage_3() {
        TimeOfDay timeOfDay = TimeManager.getStaticCurrent();
        String message = getGreeting(timeOfDay);
        return message;
    }

    // 各メソッド共通の挨拶メッセージ取得処理
    private String getGreeting(TimeOfDay timeOfDay) {
        return switch(timeOfDay) {
        case MORNING -> "おはよう";
        case AFTERNOON -> "こんにちは";
        case EVENING -> "こんばんは";
        };
    }
}