package pro.kensait.java.fee.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import pro.kensait.java.fee.FeeService;

public class FeeEnclosedTest {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    // テスト対象クラス
    FeeService feeService;

    /*
     *  各テストケースで共通的な事前処理
     */
    @BeforeEach
    void setUp() {
        feeService = new FeeService();
    }

    @Nested
    class ToOurBank {
        @Test
        void test_CalcFee_ToOurBank_Over30000_RightFee() {
            int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
            assertEquals(0, actual);
        }

        @Test
        void test_CalcFee_ToOurBank_Under30000_RightFee() {
            int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
            assertEquals(100, actual);
        }
    }

    @Nested
    class ToOtherBank {
        @Test
        void test_CalcFee_ToOtherBank_Over40000_RightFee() {
            int actual = feeService.calcFee(OTHER_BANK_CODE, 40999);
            assertEquals(200, actual);
        }

        @Test
        void test_CalcFee_ToOtherBank_Under40000_RightFee() {
            int actual = feeService.calcFee(OTHER_BANK_CODE, 39000);
            assertEquals(500, actual);
        }
    }
}