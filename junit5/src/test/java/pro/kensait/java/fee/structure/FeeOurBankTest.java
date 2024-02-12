package pro.kensait.java.fee.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pro.kensait.java.fee.FeeService;

public class FeeOurBankTest {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行

    /*
     *  各テストケースで共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    FeeService feeService;

    /*
     *  各テストケースで共通的な事前処理
     */
    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void test_CalcFee_ToOurBank_Over30000_RightFee() {
        int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
        assertEquals(0, actual);
    } 

    @Test
    public void test_CalcFee_ToOurBank_Under30000_RightFee() {
        int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
        assertEquals(100, actual);
    }
}