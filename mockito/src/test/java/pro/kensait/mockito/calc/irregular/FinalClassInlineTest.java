package pro.kensait.mockito.calc.irregular;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pro.kensait.mockito.calc.irregular.FinalCalc;

public class FinalClassInlineTest {

    FinalCalc mock;

    @BeforeEach
    void setUp() {
        mock = mock(FinalCalc.class);
    }

    @Test
    void test() {
        when(mock.compute(5, 10, 3)).thenReturn(50);
        int answer1 = mock.compute(5, 10, 3);
        System.out.println("answer1 => " + answer1);
    }
}