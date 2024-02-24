package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 * @MethodSourceに指定されたスタティックメソッドの呼び出し結果から、パラメータを読み込む
 */
public class FeeParameterTest_2 {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    // テスト対象クラス
    FeeService feeService;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @ParameterizedTest
    @MethodSource("paramProvider")
    @DisplayName("手数料計算のテスト（スタティックメソッドからパラメータを取得する）")
    void test_CalcFee(Parameter param) {
        // 実行フェーズ
        int actual = feeService.calcFee(param.bankCode, param.amount);
        // 検証フェーズ
        assertEquals(param.expectedFee, actual);
    }

    // パラメータを提供するスタティックメソッド
    static Stream<Parameter> paramProvider() {
        return Stream.of(
            new Parameter(OUR_BANK_CODE, 30999, 0),
            new Parameter(OUR_BANK_CODE, 29000, 100),
            new Parameter(OTHER_BANK_CODE, 40000, 200),
            new Parameter(OTHER_BANK_CODE, 39999, 500)
        );
    }

    // パラメータを表すクラス
    static class Parameter {
        String bankCode; // 銀行コード
        int amount; // 金額
        int expectedFee; // 期待値（手数料）
        Parameter(String bankCode, int amount, int expectedFee) {
            this.bankCode = bankCode;
            this.amount = amount;
            this.expectedFee = expectedFee;
        }
    }
}