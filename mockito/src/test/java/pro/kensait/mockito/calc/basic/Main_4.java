package pro.kensait.mockito.calc.basic;

import static org.mockito.Mockito.*;

import pro.kensait.java.calc.Calc_4;

public class Main_4 {

    public static void main(String[] args) {
        // Calc_4クラスのモックを振る舞い毎に生成する
        Calc_4 calc1 = new Calc_4(5, 10, 8);
        Calc_4 mock1 = spy(calc1);
        Calc_4 calc2 = new Calc_4(5, 10, 3);
        Calc_4 mock2 = spy(calc2);
        Calc_4 calc3 = new Calc_4(5, 10, -1);
        Calc_4 mock3 = spy(calc3);

        // モックの振る舞いを定義する
        when(mock1.compute()).thenReturn(100); // ケース1
        when(mock2.compute()).thenReturn(50); // ケース2
        when(mock3.compute()).thenThrow(new IllegalArgumentException()); // ケース3

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = mock1.compute();
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース2の挙動を確認する
            int answer2 = mock2.compute();
            System.out.println("answer2 => " + answer2);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース3の挙動を確認する
            int answer3 = mock3.compute();
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}