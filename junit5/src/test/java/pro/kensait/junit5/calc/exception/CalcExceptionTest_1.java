package pro.kensait.junit5.calc.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Calculator（計算機）を対象にしたテストクラス
 * fail()を利用する
 */
public class CalcExceptionTest_1 {

    @Test
    @DisplayName("掛け算を行い、極度を超えない場合は例外が発生しないことをテストする")
    void test_Multiply_No_Exception() {
        Calculator calc = new Calculator();
        try {
            calc.multiply1(100_000, 5);
        } catch(IllegalArgumentException iae) {
            fail(); // 失敗させる
        }
        // ここまで進んだら成功
    }

    @Test
    @DisplayName("掛け算を行い、極度オーバーした場合に例外が発生することをテストする")
    void test_Multiply_Exception() {
        Calculator calc = new Calculator();
        try {
            calc.multiply1(100_000, 15);
        } catch(IllegalArgumentException iae) {
            assertEquals("LIMIT OVER OCCURED!", iae.getMessage());
            return; // 成功
        }
        fail(); // 失敗させる
    }
}