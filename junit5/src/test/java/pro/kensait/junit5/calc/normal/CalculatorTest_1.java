package pro.kensait.junit5.calc.normal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pro.kensait.junit5.calc.normal.Calculator;

/*
 * Calculatorクラス（通常の計算機）のためのテストクラス
 */
public class CalculatorTest_1 {
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