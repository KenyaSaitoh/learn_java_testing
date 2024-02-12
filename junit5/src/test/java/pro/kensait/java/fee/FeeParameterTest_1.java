package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class FeeParameterTest_1 {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    // 各テストケースで共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    FeeService feeService;

    // 各テストケースで共通的な事前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @ParameterizedTest
    @MethodSource("fixtureProvider")
    @DisplayName("手数料計算のテスト（メソッドからパラメータを取得する）")
    void test_CalcFee(Fixture f) {
        // 実行フェーズ
        int actual = feeService.calcFee(f.bankCode, f.amount);
        // 検証フェーズ
        assertEquals(f.expectedFee, actual);
    }

    // パラメータを提供するメソッド
    static Stream<Fixture> fixtureProvider() {
        return Stream.of(
            new Fixture(OUR_BANK_CODE, 30999, 0),
            new Fixture(OUR_BANK_CODE, 29000, 100),
            new Fixture(OTHER_BANK_CODE, 40000, 200),
            new Fixture(OTHER_BANK_CODE, 39999, 500)
        );
    }

    // フィクスチャを表すクラス
    static class Fixture {
        String bankCode; // 振込先銀行コード
        int amount; // 金額
        int expectedFee; // 期待値（手数料）
        Fixture(String bankCode, int amount, int expectedFee) {
            this.bankCode = bankCode;
            this.amount = amount;
            this.expectedFee = expectedFee;
        }
    }
}