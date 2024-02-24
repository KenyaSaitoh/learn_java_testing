package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * FeeService（手数料計算サービス）を対象にしたテストクラス
 */
public class FeeServiceTest {
    public static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    public static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    // テスト対象クラス
    FeeService feeService;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
     // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @Test
    @DisplayName("自分の銀行宛に30000円を振り込み、手数料が0円であることをテストする")
    void test_CalcFee_ToOurBank_Over30000_RightFee() {
        // 実行フェーズ
        int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
        // 検証フェーズ
        assertEquals(0, actual);
    } 

    @Test
    @DisplayName("自分の銀行宛に29999円を振り込み、手数料が100円であることをテストする")
    void test_CalcFee_ToOurBank_Under30000_RightFee() {
        // 実行フェーズ
        int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
        // 検証フェーズ
        assertEquals(100, actual);
    }

    @Test
    @DisplayName("他の銀行宛に40000円を振り込み、手数料が200円であることをテストする")
    void test_CalcFee_ToOtherBank_Over40000_RightFee() {
        // 実行フェーズ
        int actual = feeService.calcFee(OTHER_BANK_CODE, 40000);
        // 検証フェーズ
        assertEquals(200, actual);
    }

    @Test
    @DisplayName("他の銀行宛に39999円を振り込み、手数料が500円であることをテストする")
    void test_CalcFee_ToOtherBank_Under40000_RightFee() {
        // 実行フェーズ
        int actual = feeService.calcFee(OTHER_BANK_CODE, 39999);
        // 検証フェーズ
        assertEquals(500, actual);
    }
}