package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 * @MethodSourceに指定されたスタティックメソッドの呼び出し結果から、パラメータを読み込む
 */
@DisplayName("振込手数料計算サービスのテストクラス3")
public class FeeParameterTest_3 {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    @ParameterizedTest(name = "順番:{index} ; 引数:{arguments}")
    @MethodSource("paramProvider")
    @DisplayName("スタティックメソッドからパラメータを取得するテストメソッド")
    void test_CalcFee(FeeParam param) {
        FeeService feeService = new FeeService();
        int actual = feeService.calcFee(param.bankCode, param.amount);
        assertEquals(param.expectedFee, actual);
    }

    // パラメータを提供するスタティックメソッド
    static Stream<FeeParam> paramProvider() {
        return Stream.of(
            new FeeParam(OUR_BANK_CODE, 30999, 0),
            new FeeParam(OUR_BANK_CODE, 29000, 100),
            new FeeParam(OTHER_BANK_CODE, 40000, 200),
            new FeeParam(OTHER_BANK_CODE, 39999, 500)
        );
    }

    // パラメータを表す入れ子クラス
    static class FeeParam {
        String bankCode; // 銀行コード
        int amount; // 金額
        int expectedFee; // 期待値（手数料）
        FeeParam(String bankCode, int amount, int expectedFee) {
            this.bankCode = bankCode;
            this.amount = amount;
            this.expectedFee = expectedFee;
        }
        @Override
        public String toString() {
            return "FeeParam [bankCode=" + bankCode + ", amount=" + amount + ", expectedFee="
                    + expectedFee + "]";
        }
    }
}