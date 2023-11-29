package pro.kensait.java.calc.case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    
    Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testMultiply() {
        try {
            int actual = calc.multiply(100_000, 5);
            assertEquals(500_000, actual);

        } catch(LimitOverException loe) {
            fail("LimitOverException発生");
        }
    }

    @Test
    public void testMultiplyExpectedLimitOverException() {
        LimitOverException exception = assertThrows(LimitOverException.class, () -> {
            calc.multiply(100_000, 15);
        });

        assertEquals("calc result is 1500000", exception.getMessage());
    }
}
