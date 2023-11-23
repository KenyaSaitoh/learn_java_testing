package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FeeEnclosedTest2 {
    private static final String OUR_BANK_CODE = "B001"; // 自行
    private static final String OTHER_BANK_CODE = "B999"; // 他行

    @Nested
    class 自行宛振込 {
        FeeService feeService;

        @BeforeEach
        void setUp() {
            feeService = new FeeService();
        }

        @Test
        void 金額30000円以上の振込手数料計算() {
            int actual = feeService.calcFee(OUR_BANK_CODE, 30000);
            assertEquals(0, actual);
        }

        @Test
        void 金額30000円未満の振込手数料計算() {
            int actual = feeService.calcFee(OUR_BANK_CODE, 29999);
            assertEquals(100, actual);
        }
    }

    @Nested
    class 他行宛振込 {
        FeeService feeService;

        @BeforeEach
        void setUp() {
            feeService = new FeeService();
        }

        @Test
        void 金額40000円以上の振込手数料計算() {
            int actual = feeService.calcFee(OTHER_BANK_CODE, 40000);
            assertEquals(200, actual);
        }

        @Test
        void 金額40000円未満の振込手数料計算() {
            int actual = feeService.calcFee(OTHER_BANK_CODE, 39999);
            assertEquals(500, actual);
        }
    }
}