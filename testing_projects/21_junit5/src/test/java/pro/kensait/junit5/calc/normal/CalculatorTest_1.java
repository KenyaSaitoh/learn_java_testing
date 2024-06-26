package pro.kensait.junit5.calc.normal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Calculatorを対象にしたテストクラス
 * テストフィクスチャ（テスト対象クラス）を、テストメソッド内でセットアップ
 */
@DisplayName("Calculatorを対象にしたテストクラス")
public class CalculatorTest_1 {

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

    @Test
    @DisplayName("引き算のテスト")
    void test_Subtract() {
        // 準備フェーズ
        Calculator calc = new Calculator();
        // 実行＋検証フェーズ
        assertEquals(20, calc.subtract(30, 10));
    }
}