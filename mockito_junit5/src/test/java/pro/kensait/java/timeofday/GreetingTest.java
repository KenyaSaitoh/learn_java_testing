package pro.kensait.java.timeofday;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    @Mock
    TimeManagerIF mock;

    /*
     *  各テストメソッド呼び出しの前処理（共通変数の初期化など）
     */
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        MockitoAnnotations.openMocks(this);
    }

    /*
     * 時間帯が朝の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_1_ReturnMorningMessage() {
        // モック化されたTimeManagerの振る舞いを決める
        when(mock.getCurrent()).thenReturn(TimeOfDay.MORNING);

        // モックをテスト対象クラスに注入する（依存性注入）
        greeting = new Greeting(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("おはよう", greeting.getMessage_1());
    }

    /*
     * 時間帯が昼の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_1_ReturnAfternoonMessage() {
        // モック化されたTimeManagerの振る舞いを決める
        when(mock.getCurrent()).thenReturn(TimeOfDay.AFTERNOON);

        // モックをテスト対象クラスに注入する（依存性注入）
        greeting = new Greeting(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("こんにちは", greeting.getMessage_1());
    }

    /*
     * 時間帯が夜の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_1_ReturnEveningMessage() {
        // モック化されたTimeManagerの振る舞いを決める
        when(mock.getCurrent()).thenReturn(TimeOfDay.EVENING);

        // モックをテスト対象クラスに注入する（依存性注入）
        greeting = new Greeting(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("こんばんは", greeting.getMessage_1());
    }
}