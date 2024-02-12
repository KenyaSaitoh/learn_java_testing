package pro.kensait.java.calc.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcExceptionTest_1 {
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
    public void test_Multiply_No_Exception() {
        try {
            calc.multiply1(100_000, 5);
        } catch(IllegalArgumentException iae) {
            fail(); // 失敗したとみなす
        }
    }

    @Test
    public void test_Multiply_Exception_1() {
        try {
            calc.multiply1(100_000, 15);
            fail(); // 失敗したとみなす
        } catch(IllegalArgumentException iae) {
            return; // 成功したとみなす
        }
        fail(); // 失敗したとみなす
    }

    @Test
    public void test_Multiply_Exception_2() {
        try {
            calc.multiply1(100_000, 15);
            fail(); // 失敗したとみなす
        } catch(IllegalArgumentException iae) {
            assertEquals("LIMIT OVER OCCURED!", iae.getMessage());
            return;
        }
        fail(); // 失敗したとみなす
    }
}