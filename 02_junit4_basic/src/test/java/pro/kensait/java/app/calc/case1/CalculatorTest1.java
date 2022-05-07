package pro.kensait.java.app.calc.case1;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class CalculatorTest1 {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        int actual = calc.add(30, 10);

        // Assert.assertThatではなくorg.hamcrest.MatcherAssert.assertThatを使用する.
        assertThat(40, is(actual));
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        int actual = calc.subtract(30, 10);
        assertThat(20, is(actual));
    }
}