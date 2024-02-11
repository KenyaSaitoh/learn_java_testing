package pro.kensait.java.calc.case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcExceptionTest {
    /*
     *  すべてのテストメソッドに共通的なフィクスチャを、フィールドとして宣言する
     */
    Calculator calc;

    /*
     *  各テストメソッド呼び出しの事前処理
     */
    @BeforeEach
    public void setUp() {
        // 各テストメソッドで共通的なフィクスチャを設定する
        calc = new Calculator();
    }

    @Test
    public void test_Multiply_Exception_1() {
        assertThrows(RuntimeException.class, () -> {
            calc.multiply1(100_000, 15);
        });
    }

    @Test
    public void test_Multiply_Exception_2() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
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