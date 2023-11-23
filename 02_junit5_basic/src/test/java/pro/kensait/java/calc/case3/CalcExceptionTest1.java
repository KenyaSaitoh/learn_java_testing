package pro.kensait.java.calc.case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcExceptionTest1 {
    Calculator calc;

    @BeforeEach
    public void setUp() {
        calc = new Calculator();
    }

    @Test
    public void testMultiplyExpectedLimitOverException_1() {
        assertThrows(LimitOverException.class, () -> {
            calc.multiply(100_000, 15);
        });
    }

    @Test
    public void testMultiplyExpectedLimitOverException_2() {
        LimitOverException thrown = assertThrows(LimitOverException.class,
                () -> {
            calc.multiply(100_000, 15);
        });
        assertEquals("calc result is 1500000", thrown.getMessage());
    }
}
