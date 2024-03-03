package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * 計算機インタフェース（CaclIF）をモック化するテストクラス
 * インタフェースと実装は分離する
 * mock()メソッドでモック化する
 */
public class CalculatorTest_1 {
    // モック
    CalcIF mock;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // CalcIFのモックを生成する
        mock = mock(CalcIF.class);

        // モックの振る舞いを先にすべて設定する
        // ケース1の振る舞い
        when(mock.compute(5, 10, 3)).thenReturn(50);
        // ケース2の振る舞い
        when(mock.compute(5, 10, 8)).thenReturn(100);
        // ケース3の振る舞い（例外発生）
        when(mock.compute(5, 10, -1)).thenThrow(new IllegalArgumentException("エラー"));
    }

    @Test
    @DisplayName("ケース1の振る舞いをテストする")
    void test_Case_1() {
        int answer = mock.compute(5, 10, 3);
        assertEquals(50, answer);
    }

    @Test
    @DisplayName("ケース2の振る舞いをテストする")
    void test_Case_2() {
        int answer = mock.compute(5, 10, 8);
        assertEquals(100, answer);
    }

    @Test
    @DisplayName("ケース3の振る舞い（例外発生）をテストする")
    void test_Case_3() {
        assertThrows(IllegalArgumentException.class, () -> mock.compute(5, 10, -1));
    }
}