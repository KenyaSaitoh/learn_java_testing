package pro.kensait.java.app.calc.case4;

public class Calculator {
    private static final int limit = 1000; // 極度

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
        int result = 0;
        if (limit < result) {
            // ビジネスロジックでエラー（極度オーバー）が発生
            throw new LimitOverException();
        }
        return result;
    }

    // 割り算を実行する
    public double divide(int param1, int param2) throws ZeroDivideException {
        if (param2 == 0) {
            // ビジネスロジックでエラー（ゼロ割り）が発生
            throw new ZeroDivideException();
        }
        return (double)param1 / (double)param2;
    }
}