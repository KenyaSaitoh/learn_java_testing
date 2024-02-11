package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeeOtherBankTest {
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    /*
     *  すべてのテストメソッドに共通的なフィクスチャを、フィールドとして宣言する
     */
    
    // テスト対象クラス
    FeeService feeService;

    /*
     *  各テストメソッド呼び出しの事前処理
     */
    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void test_CalcFee_ToOtherBank_Over40000_RightFee() {
        int actual = feeService.calcFee(OTHER_BANK_CODE, 40000);
        assertEquals(200, actual);
    }

    @Test
    public void test_CalcFee_ToOtherBank_Under40000_RightFee() {
        int actual = feeService.calcFee(OTHER_BANK_CODE, 39999);
        assertEquals(500, actual);
    }
}