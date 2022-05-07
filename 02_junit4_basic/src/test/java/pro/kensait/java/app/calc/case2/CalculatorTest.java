package pro.kensait.java.app.calc.case2;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator(30, 10);
    }

    @Test
    public void testAdd() {
        calc.add();
        int actual = calc.getAnswer();
        assertThat(40, is(actual));
    }

    @Test
    public void testSubtract() {
        calc.subtract();
        int actual = calc.getAnswer();
        assertThat(20, is(actual));
    }
}