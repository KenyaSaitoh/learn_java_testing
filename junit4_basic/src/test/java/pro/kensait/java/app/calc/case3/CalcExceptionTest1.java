package pro.kensait.java.app.calc.case3;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalcExceptionTest1 {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    @SuppressWarnings("unused")
    @Test(expected = LimitOverException.class)
    public void testMultiplyExpectedLimitOverException_1() throws LimitOverException {
        int actual = calc.multiply(100_000, 15);
    }

    @SuppressWarnings("unused")
    @Test
    public void testMultiplyExpectedLimitOverException_2() throws LimitOverException {
        // まずExpectedExceptionにルールを設定する
        exceptionRule.expect(LimitOverException.class);
        exceptionRule.expectMessage("calc result is 1500000");
        // 実行する
        int actual = calc.multiply(100_000, 15);
    }
}