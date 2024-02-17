package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * 計算機インタフェース（CaclIF）をモック化するテストクラス
 * ArgumentMatchersによって、引数マッチングを行う
 */
public class DynamicArgTest {
    // モック
    @Mock
    CalcIF mock;

    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);

        // モックの振る舞いを先にすべて設定する
        // ケース1
        when(mock.compute(anyInt(), anyInt(), eq(1))).thenReturn(50);
        // ケース2
        when(mock.compute(anyInt(), anyInt(), eq(2))).thenReturn(100);
        // ケース3
        when(mock.compute(anyInt(), anyInt(), eq(-1)))
                .thenThrow(new IllegalArgumentException("エラー"));
    }

    @Test
    @DisplayName("ケース1の振る舞いをテストする")
    void testest_Case_1() {
        int answer = mock.compute(0, 0, 1);
        assertEquals(50, answer);
    }

    @Test
    @DisplayName("ケース2の振る舞いをテストする")
    void testest_Case_2() {
        int answer = mock.compute(0, 0, 2);
        assertEquals(100, answer);
    }

    @Test
    @DisplayName("ケース3の振る舞い（例外発生）をテストする")
    void testest_Case_3() {
        assertThrows(IllegalArgumentException.class, () -> mock.compute(0, 0, -1));
    }
}