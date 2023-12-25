package pro.kensait.java.calc.case3;

/*
 * ステートを保持しない（ステートレスな）計算機
 * 掛け算において、結果が一定の値（極度）を超えると例外が発生する
 */
public class Calculator {
    private static final int LIMIT = 1_000_000; // 極度

    // 足し算を実行する［1］
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    // 引き算を実行する［2］
    public int subtract(int param1, int param2) {
        return param1 - param2;
    }

    // 掛け算を実行する［3］
    public int multiply1(int param1, int param2) {
        int result = param1 * param2;
        if (LIMIT < result) { // ［4］
            // ビジネスロジックでエラー（極度オーバー）が発生
            throw new RuntimeException("LIMIT OVER OCCURED!");
        }
        return result;
    }

    // 掛け算を実行する［5］
    public int multiply2(int param1, int param2) {
        int result = param1 * param2;
        if (LIMIT < result) { // ［6］
            // ビジネスロジックでエラー（極度オーバー）が発生
            throw new LimitOverException("calc result is " + result);
        }
        return result;
    }
}