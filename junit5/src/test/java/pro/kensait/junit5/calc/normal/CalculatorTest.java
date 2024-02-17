package pro.kensait.junit5.calc.normal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * Calculator（通常の計算機）を対象にしたテストクラス
 */
public class CalculatorTest {

    // 足し算のテスト
    @Test
    void test_Add() {
        // 準備フェーズ
        Calculator calc = new Calculator();
        // 実行フェーズ
        int actual = calc.add(30, 10);
        // 検証フェーズ
        assertEquals(40, actual);
    }

    // 引き算のテスト
    @Test
    void test_Subtract() {
        // 準備フェーズ
        Calculator calc = new Calculator();
        // 実行＋検証フェーズ
        assertEquals(20, calc.subtract(30, 10));
    }
}