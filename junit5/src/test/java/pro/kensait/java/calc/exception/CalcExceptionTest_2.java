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

    // 掛け算を行い、極度オーバーした場合に例外が発生することをテストする(1)
    @Test
    public void test_Multiply_Exception_1() {
        // 汎用的な例外クラス（IllegalArgumentException）が返されることを検証する
        assertThrows(IllegalArgumentException.class, () -> {
            calc.multiply1(100_000, 15);
        });
    }

    // 掛け算を行い、極度オーバーした場合に例外が発生することをテストする(2)
    @Test
    public void test_Multiply_Exception_2() {
        // 汎用的な例外クラス（IllegalArgumentException）が返されることを検証する
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> {
            calc.multiply1(100_000, 15);
        });
        // 加えて、例外メッセージも検証する
        assertEquals("LIMIT OVER OCCURED!", thrown.getMessage());
    }

    // 掛け算を行い、極度オーバーした場合に例外が発生することをテストする(3)
    @Test
    public void test_Multiply_Exception_3() {
        // 特定の例外クラス（LimitOverException）が返されることを検証する
        assertThrows(LimitOverException.class, () -> {
            calc.multiply2(100_000, 15);
        });
    }
}