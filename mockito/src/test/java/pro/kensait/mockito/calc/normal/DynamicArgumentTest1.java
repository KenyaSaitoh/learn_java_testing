package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicArgumentTest1 {

    private static final int NORMAL_CONDITION_1 = 1;
    private static final int NORMAL_CONDITION_2 = 2;
    private static final int ERROR_CONDITION = -1;

    CalcIF mock;

    @BeforeEach
    void setUp() {
        // Calculatorクラスのモックを生成する
        mock = mock(CalcIF.class);

        // モックの振る舞いを定義する
        // ケース1
        when(mock.compute(anyInt(), anyInt(), eq(NORMAL_CONDITION_1))).thenReturn(50);
        // ケース2
        when(mock.compute(anyInt(), anyInt(), eq(NORMAL_CONDITION_2))).thenReturn(100);
        // ケース3
        when(mock.compute(anyInt(), anyInt(), eq(ERROR_CONDITION)))
                .thenThrow(new IllegalArgumentException("エラー"));

    }

    @Test
    void test1() {
        int answer = mock.compute(0, 0, NORMAL_CONDITION_1);
        assertEquals(50, answer);
    }

    @Test
    void test2() {
        int answer = mock.compute(0, 0, NORMAL_CONDITION_2);
        assertEquals(100, answer);
    }

    @Test
    void test3() {
        assertThrows(IllegalArgumentException.class, () -> mock.compute(5, 10, -1));
    }
}