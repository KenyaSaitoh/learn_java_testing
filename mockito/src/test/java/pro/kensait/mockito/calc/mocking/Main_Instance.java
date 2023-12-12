package pro.kensait.mockito.calc.mocking;

import static org.mockito.Mockito.*;

import pro.kensait.java.calc.Calculator;

public class Main_Instance {

    public static void main(String[] args) {
        // Calculatorクラスのモックを生成する
        Calculator mock = mock(Calculator.class);

        // モックの振る舞いを定義する
        // ケース1
        when(mock.compute(5, 10, 3)).thenReturn(50);
        // ケース2
        when(mock.compute(5, 10, 8)).thenReturn(100);
        // ケース3
        when(mock.compute(5, 10, -1)).thenThrow(new IllegalArgumentException("エラー"));

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
            int answer3 = mock.compute(5, 10, -1);
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}