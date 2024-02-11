package pro.kensait.java.calc.case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ステートを保持しない計算機（Calculatorクラス）をテストする
 * テストメソッド共通の前処理（@BeforeEach）で、テスト対象クラスのインスタンスを生成する
 */
public class CalculatorTest_2 {

    // テスト対象クラス
    Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void test_Add() {
        int actual = calc.add(30, 10);
        assertEquals(40, actual);
    }

    @Test
    public void test_Subtract() {
        int actual = calc.subtract(30, 10);
        assertEquals(20, actual);
    }
}