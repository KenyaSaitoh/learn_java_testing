package pro.kensait.junit5.calc.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Calculator（計算機）を対象にしたテストクラス
 * fail()を利用する
 */
class CalcExceptionTest_1 {
    // テスト対象クラス
    Calculator calc;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        calc = new Calculator();
    }

    @Test
    @DisplayName("掛け算を行い、極度を超えない場合は例外が発生しないことをテストする")
    void test_Multiply_No_Exception() {
        try {
            calc.multiply1(100_000, 5);
        } catch(IllegalArgumentException iae) {
            fail(); // 失敗したとみなす
        }
        // ここまで進んだら成功
    }

    @Test
    @DisplayName("掛け算を行い、極度オーバーした場合に例外が発生することをテストする(1)")
    void test_Multiply_Exception_1() {
        try {
            calc.multiply1(100_000, 15);
            fail(); // 失敗したとみなす
        } catch(IllegalArgumentException iae) {
            return; // 成功
        }
        fail(); // 失敗したとみなす
    }

    @Test
    @DisplayName("掛け算を行い、極度オーバーした場合に例外が発生することをテストする(2)")
    void test_Multiply_Exception_2() {
        try {
            calc.multiply1(100_000, 15);
            fail(); // 失敗したとみなす
        } catch(IllegalArgumentException iae) {
            assertEquals("LIMIT OVER OCCURED!", iae.getMessage());
            return; // 成功
        }
        fail(); // 失敗したとみなす
    }
}