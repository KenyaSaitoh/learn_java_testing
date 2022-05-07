package pro.kensait.java.app.calc.case3;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class FeeCalculatorTest {

    FeeCalculator calc;

    @Before
    public void setUp() {
        calc = new FeeCalculator();
    }

    @Test
    public void test1() {
        int actual = calc.calcFee("B001", 5000);
        assertThat(0, is(actual));
    }

    @Test
    public void test2() {
        int actual = calc.calcFee("B002", 29999);
        assertThat(110, is(actual));
    }

    @Test
    public void test3() {
        int actual = calc.calcFee("B002", 30000);
        assertThat(220, is(actual));
    }
}