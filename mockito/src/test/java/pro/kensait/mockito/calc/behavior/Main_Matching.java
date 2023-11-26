package pro.kensait.mockito.calc.behavior;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import pro.kensait.java.calc.Calculator;

public class Main_Matching {

    private static final int NORMAL_CONDITION_1 = 1;
    private static final int NORMAL_CONDITION_2 = 2;
    private static final int ERROR_CONDITION = -1;
    
    public static void main(String[] args) {
        // Calculatorクラスのモックを生成する
        Calculator mock = mock(Calculator.class);

        // モックの振る舞いを定義する
        // ケース1
        when(mock.compute(anyInt(), anyInt(), eq(NORMAL_CONDITION_1))).thenReturn(50);
        // ケース2
        when(mock.compute(anyInt(), anyInt(), eq(NORMAL_CONDITION_2))).thenReturn(100);
        // ケース3
        when(mock.compute(anyInt(), anyInt(), eq(ERROR_CONDITION)))
                .thenThrow(new IllegalArgumentException("エラー")); 

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = mock.compute(0, 0, NORMAL_CONDITION_1);
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース2の挙動を確認する
            int answer2 = mock.compute(0, 0, NORMAL_CONDITION_2);
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