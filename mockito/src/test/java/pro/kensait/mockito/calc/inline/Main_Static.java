package pro.kensait.mockito.calc.inline;

import static org.mockito.Mockito.*;

import org.mockito.MockedStatic;

import pro.kensait.java.calc.util.CalcUtil;

public class Main_Static {

    public static void main(String[] args) {
        // CalcStaticクラスのスタティックモックを生成する
        MockedStatic<CalcUtil> mock = mockStatic(CalcUtil.class);

        // モックの振る舞いを定義する
        // ケース1
        mock.when(() -> CalcUtil.compute(5, 10, 3)).thenReturn(50);
        // ケース2
        mock.when(() -> CalcUtil.compute(5, 10, 8)).thenReturn(100);
        // ケース3
        mock.when(() -> CalcUtil.compute(5, 10, -1))
                .thenThrow(new IllegalArgumentException("エラー"));

        // モックを呼び出す
        try {
            // ケース1の挙動を確認する
            int answer1 = CalcUtil.compute(5, 10, 3);
            System.out.println("answer1 => " + answer1);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース2の挙動を確認する
            int answer2 = CalcUtil.compute(5, 10, 8);
            System.out.println("answer2 => " + answer2);
        } catch (RuntimeException re) {
            System.out.println(re);
        }

        try {
            // ケース3の挙動を確認する
            int answer3 = CalcUtil.compute(5, 10, -1);
            System.out.println("answer3 => " + answer3);
        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }
}