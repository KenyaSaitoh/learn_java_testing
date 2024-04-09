package pro.kensait.junit5.calc.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Calculator（計算機）を対象にしたテストクラス
 * assertThrows()を利用する
 */
public class CalcExceptionTest_2 {

    @Test
    @DisplayName("掛け算を行い、極度オーバーした場合に例外が発生することをテストする(1)")
    void test_Multiply_Exception_1() {
        Calculator calc = new Calculator();
        // 汎用的な例外クラス（IllegalArgumentException）が返されることを検証する
        assertThrows(IllegalArgumentException.class, () -> {
            calc.multiply1(100_000, 15);
        });
    }

    @Test
    @DisplayName("掛け算を行い、極度オーバーした場合に例外が発生することをテストする(2)")
    void test_Multiply_Exception_2() {
        Calculator calc = new Calculator();
        // 汎用的な例外クラス（IllegalArgumentException）が返されることを検証する
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> {
            calc.multiply1(100_000, 15);
        });
        // 加えて、例外メッセージも検証する
        assertEquals(Calculator.LIMIT_OVER_ERROR_MESSAGE, thrown.getMessage());
    }

    @Test
    @DisplayName("掛け算を行い、極度オーバーした場合に例外が発生することをテストする(3)")
    void test_Multiply_Exception_3() {
        Calculator calc = new Calculator();
        // 特定の例外クラス（LimitOverException）が返されることを検証する
        assertThrows(LimitOverException.class, () -> {
            calc.multiply2(100_000, 15);
        });
    }
}