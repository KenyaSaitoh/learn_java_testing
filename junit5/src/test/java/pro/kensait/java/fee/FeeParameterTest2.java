package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class FeeParameterTest2 {

    FeeService feeService;

    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/parameter.csv", numLinesToSkip = 1)
    public void test_CalcFee(String bankCode, int amount, int expectedFee) {
        int actual = feeService.calcFee(bankCode, amount);
        assertEquals(expectedFee, actual);
    }
}