package pro.kensait.java.calc.case1_new;

/*
 * 特殊な機能を持った計算機
 * 4つの引数を取る
 * ①：1つ目と2つ目を加算する
 * ②：その結果に3つ目を指数演算する（PowerProcessorの機能を使う）
 * ③：その結果に4つ目を乗算して返す
 */
public class Calculator {

    // 依存先（NormalPowerCalc）インスタンスをNEW演算子で生成し、呼び出す
    public int calc(int param1, int param2, int param3, int param4) {
        int added = param1 + param2;
        PowerProcessor processor = new PowerProcessor();
        int powered = processor.power(added, param3);
        return powered * param4;
    }
}