package pro.kensait.junit5.calc.sideeffect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * StatefulCalculator（状態を保持する計算機）を対象にしたテストクラス
 */
public class StatefulCalcTest {
    // テスト対象クラス
    StatefulCalc calc;

    // 各テストケースで共通的な事前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        calc = new StatefulCalc(30, 10);
    }

    @Test
    @DisplayName("足し算のテスト")
    void test_Add() {
        // 実行フェーズ
        calc.add();
        // 検証フェーズ
        int actual = calc.getAnswer();
        assertEquals(40, actual);
    }

    @Test
    @DisplayName("引き算のテスト")
    void test_Subtract() {
        // 実行フェーズ
        calc.subtract();
        // 検証フェーズ
        int actual = calc.getAnswer();
        assertEquals(20, actual);
    }
}