package pro.kensait.java.calc.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcExceptionTest_2 {
    // 各テストケースで共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    Calculator calc;

    // 各テストケースで共通的な事前処理
    @BeforeEach
    public void setUp() {
        // 共通フィクスチャを設定する
        calc = new Calculator();
    }

    @Test
    public void test_Multiply_Exception_1() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.multiply1(100_000, 15);
        });
    }

    @Test
    public void test_Multiply_Exception_2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> {
            calc.multiply1(100_000, 15);
        });
        assertEquals("LIMIT OVER OCCURED!", thrown.getMessage());
    }

    @Test
    public void test_Multiply_Exception_3() {
        assertThrows(LimitOverException.class, () -> {
            calc.multiply2(100_000, 15);
        });
    }
}