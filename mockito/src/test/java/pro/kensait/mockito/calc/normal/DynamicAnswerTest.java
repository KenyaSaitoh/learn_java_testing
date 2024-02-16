package pro.kensait.mockito.calc.normal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

public class DynamicAnswerTest {
    private static final int ERROR_CONDITION = -1;

    CalcIF mock;

    @BeforeEach
    void setUp() {
        // Calculatorクラスのモックを生成する
        mock = mock(CalcIF.class);

        // 任意の振る舞いをAnswerとして定義する
        Answer<Integer> answer = invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            int z = invocation.getArgument(2);
            if (z == ERROR_CONDITION)
                throw new IllegalArgumentException("エラー");
            return x + y + z;
        };

        // モックの振る舞いを定義する（すべての振る舞いはAnswerで決まる）
        when(mock.compute(anyInt(), anyInt(), anyInt())).thenAnswer(answer);
    }

    @Test
    void test1() {
        int answer = mock.compute(5, 10, 3);
    }

    @Test
    void test2() {
        int answer = mock.compute(5, 10, 8);
    }

    @Test
    void test3() {
        int answer = mock.compute(0, 0, ERROR_CONDITION);
    }
}