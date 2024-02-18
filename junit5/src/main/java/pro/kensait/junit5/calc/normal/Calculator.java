package pro.kensait.junit5.calc.normal;

/*
 * 通常の（状態を保持しない）計算機を表すクラス（テスト対象）
 */
public class Calculator {

    // 足し算
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    // 引き算
    public int subtract(int param1, int param2) {
        return param1 - param2;
    }
}