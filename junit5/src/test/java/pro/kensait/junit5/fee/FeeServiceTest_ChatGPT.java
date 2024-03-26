package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FeeServiceTest_ChatGPT {

    private FeeService feeService;

    // 前処理：FeeServiceのインスタンス生成
    @BeforeEach
    void setUp() {
        feeService = new FeeService();
    }

    // 自分の銀行に対する振込で、30000円以上の場合の手数料テスト
    @Test
    @DisplayName("Test calcFee with our bank and amount >= 30000")
    void testCalcFeeOurBankAboveThreshold() {
        assertEquals(0, feeService.calcFee("B001", 30000), "自分の銀行への振込で30000円以上の場合、手数料は0円であるべき");
    }

    // 自分の銀行に対する振込で、30000円未満の場合の手数料テスト
    @Test
    @DisplayName("Test calcFee with our bank and amount < 30000")
    void testCalcFeeOurBankBelowThreshold() {
        assertEquals(100, feeService.calcFee("B001", 29999), "自分の銀行への振込で30000円未満の場合、手数料は100円であるべき");
    }

    // 他の銀行に対する振込で、40000円以上の場合の手数料テスト
    @Test
    @DisplayName("Test calcFee with other bank and amount >= 40000")
    void testCalcFeeOtherBankAboveThreshold() {
        assertEquals(200, feeService.calcFee("B999", 40000), "他の銀行への振込で40000円以上の場合、手数料は200円であるべき");
    }

    // 他の銀行に対する振込で、40000円未満の場合の手数料テスト
    @Test
    @DisplayName("Test calcFee with other bank and amount < 40000")
    void testCalcFeeOtherBankBelowThreshold() {
        assertEquals(500, feeService.calcFee("B999", 39999), "他の銀行への振込で40000円未満の場合、手数料は500円であるべき");
    }
}

