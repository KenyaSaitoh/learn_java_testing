package pro.kensait.mockito.calc.basic;

import static org.mockito.Mockito.*;

import pro.kensait.java.calc.CalcIF;
import pro.kensait.java.calc.Calc_1;

public class Main_1 {

    public static void main(String[] args) {
        // Calc_1クラスのモックをCalcIF型で生成する
        CalcIF mock = mock(Calc_1.class);

        // モックの振る舞いを定義する
        when(mock.compute(5, 10, 8)).thenReturn(100); // ケース1
        when(mock.compute(5, 10, 3)).thenReturn(50); // ケース2
        when(mock.compute(5, 10, -1)).thenThrow(new IllegalArgumentException()); // ケース3

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = mock.compute(5, 10, 8);
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース2の挙動を確認する
            int answer2 = mock.compute(5, 10, 3);
            System.out.println("answer2 => " + answer2);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース3の挙動を確認する
            int answer3 = mock.compute(5, 5, -1);
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}