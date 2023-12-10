package pro.kensait.java.calc.case1;

/*
 * ステートを保持しない（ステートレスな）計算機
 */
public class Calculator {

    // 足し算を実行する
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    // 引き算を実行する
    public int subtract(int param1, int param2) {
        return param1 - param2;
    }
}