package pro.kensait.java.calc.case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * ステートを保持しない計算機（Calculatorクラス）をテストする
 * テストメソッド内で、テスト対象クラスのインスタンスを生成する
 */
public class CalculatorTest1 {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        int actual = calc.add(30, 10);
        assertEquals(actual, 40);
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        int actual = calc.subtract(30, 10);
        assertEquals(actual, 20);
    }
    
    @Test
    public void testFail() {
        // fail("意図的に失敗させる");
    }
}