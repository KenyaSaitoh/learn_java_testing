package pro.kensait.mockito.calc.stateful;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/*
 * 状態を持つ計算機（StatefulCalc）をスパイ化するテストクラス
 */
public class CalcSpyTest {
    // スパイ
    @Spy
    StatefulCalc spy1 = new StatefulCalc(5, 10, 3); // ケース1
    @Spy
    StatefulCalc spy2 = new StatefulCalc(5, 10, 8);  // ケース1

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Spyアノテーションが付与されたフィールドをスパイ化する
        MockitoAnnotations.openMocks(this);

        // モックの振る舞いを先にすべて設定する
        // ケース1の振る舞い
        when(spy1.compute()).thenReturn(50);
        // ケース2の振る舞い
        when(spy2.compute()).thenReturn(100);
    }

    @Test
    @DisplayName("ケース1の振る舞いをテストする")
    void test_Case_1() {
        int answer = spy1.compute();
        assertEquals(50, answer);
    }

    @Test
    @DisplayName("ケース2の振る舞いをテストする")
    void test_Case_2() {
        int answer = spy2.compute();
        assertEquals(100, answer);
    }
}