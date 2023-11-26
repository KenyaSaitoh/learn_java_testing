package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeeServiceTest {
    private static final String OUR_BANK_CODE = "B001"; // 自行
    private static final String OTHER_BANK_CODE = "B999"; // 他行

    FeeService feeService;

    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void calcFee_ToOurBank_Over30000_ReturnsRightFee() {
        int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
        assertEquals(0, actual);
    } 

    @Test
    public void calcFee_ToOurBank_Under30000_ReturnsRightFee() {
        int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
        assertEquals(100, actual);
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