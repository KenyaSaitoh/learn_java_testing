package pro.kensait.java.timeofday;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * Greetingを対象にしたテストクラス
 */
public class GreetingTest {
    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    Greeting greeting;

    // テスト対象クラスが依存しているクラス（モック対象）
    TimeManagerIF mock;

    /*
     * 時間帯が朝の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_ReturnMorningMessage() {
        // モックを生成する
        mock = new MockTimeManager(TimeOfDay.MORNING);

        // モックをテスト対象クラスに注入する（依存性注入）
        greeting = new Greeting(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("おはよう", greeting.getMessage_1());
    }

    /*
     * 時間帯が昼の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_ReturnAfternoonMessage() {
        // モックを生成する
        mock = new MockTimeManager(TimeOfDay.AFTERNOON);

        // モックをテスト対象クラスに注入する（依存性注入）
        greeting = new Greeting(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("こんにちは", greeting.getMessage_1());
    }

    /*
     * 時間帯が夜の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_ReturnEveningMessage() {
        // モックを生成する
        mock = new MockTimeManager(TimeOfDay.EVENING);

        // モックをテスト対象クラスに注入する（依存性注入）
        greeting = new Greeting(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("こんばんは", greeting.getMessage_1());
    }
}