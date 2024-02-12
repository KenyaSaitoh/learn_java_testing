package pro.kensait.java.calc.normal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Calculatorクラス（通常の計算機）のためのテストクラス
 */
public class CalculatorTest_2 {
    // 各テストケースで共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    Calculator calc;

    // 各テストケースで共通的な事前処理
    @BeforeEach
    public void setUp() {
        // 共通フィクスチャを設定する
        calc = new Calculator();
    }

    // 足し算のテスト
    @Test
    public void test_Add() {
        // 実行フェーズ
        int actual = calc.add(30, 10);
        // 検証フェーズ
        assertEquals(40, actual);
    }

    // 引き算のテスト
    @Test
    public void test_Subtract() {
        // 実行＋検証フェーズ
        assertEquals(20, calc.subtract(30, 10));
    }
}