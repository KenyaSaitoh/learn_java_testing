package pro.kensait.java.app.calc.case3;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalcExceptionTest2 {

    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    // JUnit4.13以降
    @Test
    public void testMultiplyExpectedLimitOverException_4() throws LimitOverException {
        LimitOverException loe = assertThrows(LimitOverException.class,
                () -> calc.multiply(100_000, 15));
        assertThat(loe.getMessage(), is("calc result is 1500000"));
    }
}