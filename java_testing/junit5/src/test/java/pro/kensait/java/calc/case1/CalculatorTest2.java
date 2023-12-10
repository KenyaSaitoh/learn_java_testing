package pro.kensait.java.calc.case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ステートを保持しない計算機（Calculatorクラス）をテストする
 * テストメソッド共通の前処理（@BeforeEach）で、テスト対象クラスのインスタンスを生成する
 */
public class CalculatorTest2 {

    // テスト対象クラス
    Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        int actual = calc.add(30, 10);
        assertEquals(actual, 40);
    }

    @Test
    public void testSubtract() {
        int actual = calc.subtract(30, 10);
        assertEquals(actual, 20);
    }
}