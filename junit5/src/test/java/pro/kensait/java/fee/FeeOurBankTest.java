package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeeOurBankTest {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行

    FeeService feeService;

    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void test_CalcFee_ToOurBank_Over30000_ReturnsRightFee() {
        int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
        assertEquals(0, actual);
    } 

    @Test
    public void test_CalcFee_ToOurBank_Under30000_ReturnsRightFee() {
        int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
        assertEquals(100, actual);
    }
}