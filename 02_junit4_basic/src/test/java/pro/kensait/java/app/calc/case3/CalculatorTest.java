package pro.kensait.java.app.calc.case3;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testMultiply() throws LimitOverException {
        try {
            int actual = calc.multiply(100_000, 5);
            assertThat(500_000, is(actual));

        } catch(LimitOverException loe) {
            fail("LimitOverException発生");
        }
    }

    @SuppressWarnings("unused")
    @Test
    public void testMultiplyExpectedLimitOverException() throws LimitOverException {
        try {
            int actual = calc.multiply(100_000, 15);
            fail("LimitOverException発生");

        } catch(LimitOverException loe) {
            assertThat(loe.getMessage(), is("calc result is 1500000"));
        }
    }
}