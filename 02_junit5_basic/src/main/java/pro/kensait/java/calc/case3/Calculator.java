package pro.kensait.java.calc.case3;

public class Calculator {
    private static final int LIMIT = 1_000_000; // 極度

    // 足し算を実行する
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    // 引き算を実行する
    public int subtract(int param1, int param2) {
        return param1 - param2;
    }

    // 掛け算を実行する
    public int multiply(int param1, int param2) throws LimitOverException {
        int result = param1 * param2;
        if (LIMIT < result) {
            // ビジネスロジックでエラー（極度オーバー）が発生
            throw new LimitOverException("calc result is " + result);
        }
        return result;
    }
}