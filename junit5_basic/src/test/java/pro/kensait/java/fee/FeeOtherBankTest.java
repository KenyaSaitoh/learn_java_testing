package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeeOtherBankTest {
    private static final String OTHER_BANK_CODE = "B999"; // 他行

    FeeService feeService;

    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void calcFee_ToOtherBank_Over40000_ReturnsRightFee() {
        int actual = feeService.calcFee(OTHER_BANK_CODE, 40000);
        assertEquals(200, actual);
    }

    @Test
    public void calcFee_ToOtherBank_Under40000_ReturnsRightFee() {
        int actual = feeService.calcFee(OTHER_BANK_CODE, 39999);
        assertEquals(500, actual);
    }
}