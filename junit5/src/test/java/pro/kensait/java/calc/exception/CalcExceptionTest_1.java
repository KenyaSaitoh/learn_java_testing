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

    // 掛け算を行い、極度を超えない場合は例外が発生しないことをテストする
    @Test
    public void test_Multiply_No_Exception() {
        try {
            calc.multiply1(100_000, 5);
        } catch(IllegalArgumentException iae) {
            fail(); // 失敗したとみなす
        }
        // ここまで進んだら成功
    }

    // 掛け算を行い、極度オーバーした場合に例外が発生することをテストする(1)
    @Test
    public void test_Multiply_Exception_1() {
        try {
            calc.multiply1(100_000, 15);
            fail(); // 失敗したとみなす
        } catch(IllegalArgumentException iae) {
            return; // 成功
        }
        fail(); // 失敗したとみなす
    }

    // 掛け算を行い、極度オーバーした場合に例外が発生することをテストする(2)
    @Test
    public void test_Multiply_Exception_2() {
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