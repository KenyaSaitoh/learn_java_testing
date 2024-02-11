package pro.kensait.java.calc.case2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ステートを保持する計算機（Calculatorクラス）をテストする
 * テストメソッド共通の前処理（@BeforeEach）で、テスト対象クラスのインスタンスを生成する
 */
public class CalculatorTest {
    /*
     *  すべてのテストメソッドに共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    Calculator calc;

    /*
     *  各テストメソッド呼び出しの事前処理
     */
    @BeforeEach
    public void setUp() {
        // 各テストメソッドで共通的なフィクスチャを設定する
        calc = new Calculator(30, 10);
    }

    @Test
    public void test_Add() {
        calc.add();
        int actual = calc.getAnswer();
        assertEquals(40, actual);
    }

    @Test
    public void test_Subtract() {
        calc.subtract();
        int actual = calc.getAnswer();
        assertEquals(20, actual);
    }
}