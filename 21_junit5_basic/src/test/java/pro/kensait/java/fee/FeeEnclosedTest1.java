package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FeeEnclosedTest1 {
    private static final String OUR_BANK_CODE = "B001"; // 自行
    private static final String OTHER_BANK_CODE = "B999"; // 他行

    @Nested
    class ToOurBank {
        FeeService feeService;

        @BeforeEach
        void setUp() {
            feeService = new FeeService();
        }

        @Test
        void calcFee_ToOurBank_Over30000_ReturnsRightFee() {
            int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
            assertEquals(0, actual);
        }

        @Test
        void calcFee_ToOurBank_Under30000_ReturnsRightFee() {
            int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
            assertEquals(100, actual);
        }
    }

    @Nested
    class ToOtherBank {
        FeeService feeService;

        @BeforeEach
        void setUp() {
            feeService = new FeeService();
        }

        @Test
        void calcFee_ToOtherBank_Over40000_ReturnsRightFee() {
            int actual = feeService.calcFee(OTHER_BANK_CODE, 40999);
            assertEquals(200, actual);
        }

        @Test
        void calcFee_ToOtherBank_Under40000_ReturnsRightFee() {
            int actual = feeService.calcFee(OTHER_BANK_CODE, 39000);
            assertEquals(500, actual);
        }
    }
}