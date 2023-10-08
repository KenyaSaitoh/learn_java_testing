package pro.kensait.java.app.calc.case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
}