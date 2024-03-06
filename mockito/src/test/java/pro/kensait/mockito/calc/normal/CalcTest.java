package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * 計算機クラス（Calculator）をモック化するテストクラス
 * インタフェースと実装を分離しない
 * @Mockアノテーションでモック化する
 */
public class CalcTest {
    // モック
    @Mock
    Calculator mock;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);

        // モックの疑似的な振る舞いをすべて設定する（暗黙的セットアップ）
        // ケース1の振る舞い
        when(mock.compute(5, 10, 3)).thenReturn(50);
        // ケース2の振る舞い
        when(mock.compute(5, 10, 8)).thenReturn(100);
        // ケース3の振る舞い（例外発生）
        when(mock.compute(5, 10, -1)).thenThrow(new IllegalArgumentException());
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