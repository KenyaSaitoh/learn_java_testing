package pro.kensait.junit5.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pro.kensait.junit5.calc.normal.Calculator;

/*
 * Calculatorを対象にしたテストクラス
 */
@DisplayName("Calculatorを対象にしたテストクラス")
public class ITCalculator {

    @Test
    @DisplayName("足し算のテスト")
    void test_Add() {
        // 準備フェーズ
        Calculator calc = new Calculator();
        // 実行フェーズ
        int actual = calc.add(30, 10);
        // 検証フェーズ
        assertEquals(40, actual);
    }
}