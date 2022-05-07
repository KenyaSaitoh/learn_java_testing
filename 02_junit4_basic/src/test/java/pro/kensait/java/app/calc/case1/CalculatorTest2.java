package pro.kensait.java.app.calc.case1;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest2 {

    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }
    
    @Test
    public void testAdd() {
        int actual = calc.add(30, 10);
        assertThat(40, is(actual));
    }

    @Test
    public void testSubtract() {
        int actual = calc.subtract(30, 10);
        assertThat(20, is(actual));
    }
}