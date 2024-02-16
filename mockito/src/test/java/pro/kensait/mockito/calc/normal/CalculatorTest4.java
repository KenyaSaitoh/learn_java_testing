package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest4 {

    Calculator mock;

    @BeforeEach
    void setUp() {
        // Calculatorクラスのモックを生成する
        mock = mock(Calculator.class);

        // ここではモックの振る舞いを先に決めてしまう（テストケースごとに決めるのではなく）
        when(mock.compute(5, 10, 3)).thenReturn(50);
        when(mock.compute(5, 10, 8)).thenReturn(100);
        when(mock.compute(5, 10, -1)).thenThrow(new IllegalArgumentException("エラー"));
    }

    @Test
    void test1() {
        int answer = mock.compute(5, 10, 3);
        assertEquals(50, answer);
    }

    @Test
    void test2() {
        int answer = mock.compute(5, 10, 8);
        assertEquals(100, answer);
    }

    @Test
    void test3() {
       assertThrows(IllegalArgumentException.class, () -> mock.compute(5, 10, -1));
    }
}