package pro.kensait.java.calc.case2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator(30, 10);
    }

    @Test
    public void testAdd() {
        calc.add();
        int actual = calc.getAnswer();
        assertEquals(40, actual);
    }

    @Test
    public void testSubtract() {
        calc.subtract();
        int actual = calc.getAnswer();
        assertEquals(20, actual);
    }
}
