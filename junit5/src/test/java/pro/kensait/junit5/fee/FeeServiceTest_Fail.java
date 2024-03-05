package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 */
public class FeeServiceTest_Fail {
    public static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    public static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    // テスト対象クラス
    FeeService feeService;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
     // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @Test
    @DisplayName("自分の銀行宛に20000円を振込、手数料が0円であることをテストする")
    void test_CalcFee_ToOurBank_Over20000_RightFee() {
        // 実行フェーズ
        int actual = feeService.calcFee(OUR_BANK_CODE, 20000);
        // 検証フェーズ
        assertEquals(0, actual);
    } 
}