package pro.kensait.junit5.calc.exception;

/*
 * 状態を保持しない計算機を表すクラス（テスト対象）
 * 掛け算において、結果が一定の値（極度）を超えると例外が発生する
 */
public class Calculator {
    private static final int LIMIT = 1_000_000; // 極度
    public static final String LIMIT_OVER_ERROR_MESSAGE = "LIMIT OVER OCCURED!";

    // 掛け算を実行する
    public int multiply1(int param1, int param2) {
        int result = param1 * param2;
        if (LIMIT < result) {
            // ビジネスロジックでエラー（極度オーバー）が発生
            throw new IllegalArgumentException(LIMIT_OVER_ERROR_MESSAGE);
        }
        return result;
    }

    // 掛け算を実行する
    public int multiply2(int param1, int param2) {
        int result = param1 * param2;
        if (LIMIT < result) {
            // ビジネスロジックでエラー（極度オーバー）が発生
            throw new LimitOverException("calc result is " + result);
        }
        return result;
    }
}