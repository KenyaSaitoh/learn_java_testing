package pro.kensait.mockito.calc.irregular;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

/*
 * static修飾子を付与したユーティリティを持つ計算機（CalcUtil）をモック化するテストクラス
 */
public class CalcTest_Static {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        // CalcUtilクラスのスタティックモックを生成する
        MockedStatic<CalcUtil> mock = mockStatic(CalcUtil.class);

        // モックの疑似的な振る舞いをすべて設定する（暗黙的セットアップ）
        // ケース1
        mock.when(() -> CalcUtil.compute(5, 10, 3)).thenReturn(50);
        // ケース2
        mock.when(() -> CalcUtil.compute(5, 10, 8)).thenReturn(100);
        // ケース3
        mock.when(() -> CalcUtil.compute(5, 10, -1))
                .thenThrow(new IllegalArgumentException());
    }

    @Test
    @DisplayName("ケース1の振る舞いをテストする")
    void test_Case_1() {
        int answer = CalcUtil.compute(5, 10, 3);
        assertEquals(50, answer);
    }

    @Test
    @DisplayName("ケース2の振る舞いをテストする")
    void test_Case_2() {
        int answer = CalcUtil.compute(5, 10, 8);
        assertEquals(100, answer);
    }

    @Test
    @DisplayName("ケース3の振る舞いをテストする")
    void test_Case_3() {
        int answer = CalcUtil.compute(5, 10, -1);
        assertEquals(100, answer);
    }
}