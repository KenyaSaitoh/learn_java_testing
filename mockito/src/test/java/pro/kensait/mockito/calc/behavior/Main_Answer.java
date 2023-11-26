package pro.kensait.mockito.calc.behavior;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.stubbing.Answer;

import pro.kensait.java.calc.Calculator;

public class Main_Answer {
    private static final int ERROR_CONDITION = -1;

    public static void main(String[] args) {
        // Calculatorクラスのモックを生成する
        Calculator mock = mock(Calculator.class);

        // 任意の振る舞いをAnswerとして定義する
        Answer<Integer> answer = invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            int z = invocation.getArgument(2);
            if (z == ERROR_CONDITION) throw new IllegalArgumentException("エラー");
            return x + y + z;
        };

        // モックの振る舞いを定義する（すべての振る舞いはAnswerで決まる）
        when(mock.compute(anyInt(), anyInt(), anyInt())).thenAnswer(answer);

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = mock.compute(5, 10, 3);
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース2の挙動を確認する
            int answer2 = mock.compute(5, 10, 8);
            System.out.println("answer2 => " + answer2);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース3の挙動を確認する
            int answer3 = mock.compute(0, 0, ERROR_CONDITION);
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}