package pro.kensait.mockito.calc.basic;

import static org.mockito.Mockito.*;

import org.mockito.MockedStatic;

import pro.kensait.java.calc.Calc_3;

public class Main_3 {

    public static void main(String[] args) {
        // Calc_3クラスのスタティックモックを生成する
        MockedStatic<Calc_3> mock = mockStatic(Calc_3.class);

        // モックの振る舞いを定義する
        // ケース1
        mock.when(() -> Calc_3.compute(5, 10, 8)).thenReturn(100);
        // ケース2
        mock.when(() -> Calc_3.compute(5, 10, 3)).thenReturn(50);
        // ケース3
        mock.when(() -> Calc_3.compute(5, 10, -1)).thenThrow(new IllegalArgumentException());

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = Calc_3.compute(5, 10, 8);
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース2の挙動を確認する
            int answer2 = Calc_3.compute(5, 10, 3);
            System.out.println("answer2 => " + answer2);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース3の挙動を確認する
            int answer3 = Calc_3.compute(5, 10, -1);
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}