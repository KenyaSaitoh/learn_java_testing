package pro.kensait.junit5.fee.template;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import pro.kensait.junit5.fee.FeeService;

public class FeeTestTemplateTest {

    public static final String OUR_BANK_CODE = "B001";
    public static final String OTHER_BANK_CODE = "B999";

    FeeService feeService;

    @BeforeEach
    void setUp() {
        feeService = new FeeService();
    }

    @TestTemplate
    @ExtendWith(FeeTestTemplateProvider.class)
    @DisplayName("テストテンプレートによる手数料計算のテスト")
    void test_CalcFee(String bankCode, int amount, int expectedFee) {
        int actual = feeService.calcFee(bankCode, amount);
        assertEquals(expectedFee, actual);
    }
}
