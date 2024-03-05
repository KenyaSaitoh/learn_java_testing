package pro.kensait.mockito.calc.irregular;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * final修飾子を付与した継承不可の計算機（FinalCalc）をモック化するテストクラス
 */
public class CalcTest_Final {
    @Mock
    FinalCalc mock;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);

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