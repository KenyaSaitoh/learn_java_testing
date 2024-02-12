package pro.kensait.java.calc.sideeffect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Calculatorクラス（状態を保持する計算機）のためのテストクラス
 */
public class CalculatorTest {
    /*
     *  各テストケースで共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    Calculator calc;

    /*
     *  各テストケースで共通的な事前処理
     */
    @BeforeEach
    public void setUp() {
        // 共通フィクスチャを設定する
        calc = new Calculator(30, 10);
    }

    @Test
    public void test_Add() {
        // 実行フェーズ
        calc.add();
        // 検証フェーズ
        int actual = calc.getAnswer();
        assertEquals(40, actual);
    }

    @Test
    public void test_Subtract() {
        // 実行フェーズ
        calc.subtract();
        // 検証フェーズ
        int actual = calc.getAnswer();
        assertEquals(20, actual);
    }
}