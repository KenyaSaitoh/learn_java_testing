package pro.kensait.mockito.calc.behavior;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import pro.kensait.java.calc.normal.Calculator;

public class Main_ArgumentMatcher {

    private static final int NORMAL_CONDITION_1 = 1;
    private static final int NORMAL_CONDITION_2 = 2;
    private static final int ERROR_CONDITION = -1;
    Calculator mock;

    @BeforeEach
    void setUp() {
        // Calculatorクラスのモックを生成する
        mock = mock(Calculator.class);
    }
    
    @Test
    void test1() {
        // ArgumentMatcherを定義する
        ArgumentMatcher<Integer> isNormal1 = arg -> arg.equals(NORMAL_CONDITION_1);

        // ArgumentMatcherを使ってモックの振る舞いを定義する
        // ケース1
        when(mock.compute(anyInt(), anyInt(), argThat(isNormal1)))
                .thenReturn(50);

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = mock.compute(0, 0, NORMAL_CONDITION_1);
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
    @Test
    void test2() {
        // ケース2
        // Calculatorクラスのモックを生成する
        ArgumentMatcher<Integer> isNormal2 = arg -> arg.equals(NORMAL_CONDITION_2);

        when(mock.compute(anyInt(), anyInt(), argThat(isNormal2)))
                .thenReturn(100);

        try {
            // ケース2の挙動を確認する
            int answer2 = mock.compute(0, 0, NORMAL_CONDITION_2);
            System.out.println("answer2 => " + answer2);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
    @Test
    void test3() {
        // Calculatorクラスのモックを生成する
        ArgumentMatcher<Integer> isError = arg -> arg.equals(ERROR_CONDITION);
        // ケース3
        when(mock.compute(anyInt(), anyInt(), argThat(isError)))
                .thenThrow(new IllegalArgumentException("エラー")); 

        try {
            // ケース3の挙動を確認する
            int answer3 = mock.compute(0, 0, ERROR_CONDITION);
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}