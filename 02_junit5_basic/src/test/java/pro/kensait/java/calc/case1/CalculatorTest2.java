package pro.kensait.java.calc.case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest2 {

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