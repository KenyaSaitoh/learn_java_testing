package pro.kensait.java.calc.case4_static;

/*
 * 特殊な機能を持った計算機
 * 4つの引数を取る
 * ①：1つ目と2つ目を加算する
 * ②：その結果に3つ目を指数演算する（PowerProcessorの機能を使う）
 * ③：その結果に4つ目を乗算して返す
 */
public class Calculator {

    // 依存先（PowerProcessor）のスタティックメソッドを呼び出して計算する
    public int calc(int param1, int param2, int param3, int param4) {
        int added = param1 + param2;
        int powered = PowerProcessor.power(added, param3);
        return powered * param4;
    }
}