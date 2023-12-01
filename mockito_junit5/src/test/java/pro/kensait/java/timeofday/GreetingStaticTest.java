package pro.kensait.java.timeofday;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

/*
 * Greetingを対象にしたテストクラス
 */
public class GreetingStaticTest {
    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    Greeting greeting;

    // テスト対象クラスが依存しているクラス（スタティックモック対象）
    @Mock
    MockedStatic<TimeManager> mock;

    // 変数を初期化する
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        //MockitoAnnotations.openMocks(this);

        // TimeManagerクラスのスタティックモックを生成する
        mock = mockStatic(TimeManager.class);

        // テスト対象クラスを生成する
        greeting = new Greeting();
    }

    @AfterEach
    void tearDown() {
        mock.close(); // 静的モックを解除
    }

    /*
     * 時間帯が朝の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_3_ReturnMorningMessage() {
        // TimeManagerのスタティックメソッド呼び出し時の振る舞いを決める
        mock.when(() -> TimeManager.getStaticCurrent()).thenReturn(TimeOfDay.MORNING);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("おはよう", greeting.getMessage_3());
    }

    /*
     * 時間帯が昼の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_3_ReturnAfternoonMessage() {
        // TimeManagerのスタティックメソッド呼び出し時の振る舞いを決める
        mock.when(() -> TimeManager.getStaticCurrent()).thenReturn(TimeOfDay.AFTERNOON);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("こんにちは", greeting.getMessage_3());
    }

    /*
     * 時間帯が夜の場合、返されるメッセージをテストする
     */
    @Test
    public void testGetMessage_3_ReturnEveningMessage() {
        // TimeManagerのスタティックメソッド呼び出し時の振る舞いを決める
        mock.when(() -> TimeManager.getStaticCurrent()).thenReturn(TimeOfDay.EVENING);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals("こんばんは", greeting.getMessage_3());
    }
}