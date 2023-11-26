package pro.kensait.mockito.calc.spy;

import static org.mockito.Mockito.*;

import pro.kensait.java.calc.CalcStateful;

public class Main_Spy {

    public static void main(String[] args) {
        // CalcStatefulクラスのモック（Spy）を振る舞い毎に生成する
        CalcStateful calc1 = new CalcStateful(5, 10, 3);
        CalcStateful mock1 = spy(calc1);
        CalcStateful calc2 = new CalcStateful(5, 10, 8);
        CalcStateful mock2 = spy(calc2);
        CalcStateful calc3 = new CalcStateful(5, 10, -1);
        CalcStateful mock3 = spy(calc3);

        // モックの振る舞いを定義する
        // ケース1
        doReturn(50).when(mock1).compute();
        // 以下でも同じ挙動
        // when(mock1.compute()).thenReturn(50);

        // ケース2
        doReturn(100).when(mock2).compute();
        // 以下でも同じ挙動
        // when(mock2.compute()).thenReturn(100);

        // ケース3
        doThrow(new IllegalArgumentException("エラー")).when(mock3).compute();
        // Spyは実際に挙動してしまうので、以下では意図したとおりに動かない
        // when(mock3.compute()).thenThrow(new IllegalArgumentException("エラー"));

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