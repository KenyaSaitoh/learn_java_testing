package pro.kensait.junit5.calc.sideeffect;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pro.kensait.junit5.calc.sideeffect.OutputCalc;

/*
 * OutputCalculatorクラス（計算結果をコンソールやログに表示する計算機）のためのテストクラス
 */
public class OutputCalcTest {
    // 各テストケースで共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    OutputCalc calc;
    static PrintStream originalOut;
    static ByteArrayOutputStream output;

    // 各テストケースで共通的な事前処理
    @BeforeAll
    static void beforeAll() {
        // 標準出力をバイト配列出力ストリームに置き換える
        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    // 各テストケースで共通的な事前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        calc = new OutputCalc();
    }

    @Test
    @DisplayName("足し算のテスト")
    void test_Add() {
        // 実行フェーズ
        calc.add(30, 10);
        // 検証フェーズ
        assertEquals("40" + System.lineSeparator(), output.toString());
    }

    @Test
    @DisplayName("引き算のテスト")
    void test_Subtract() {
        // 実行フェーズ
        calc.subtract(30, 10);
        // 検証フェーズ
        assertTrue(output.toString().contains("The answer is 20"));
    }
}