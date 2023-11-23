package pro.kensait.java.app.fee;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class FeeOtherBankTest {

    FeeService feeService;

    @Before
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void calcFee_ToOtherBank_Under30000_ReturnsRightFee() {
        int actual = feeService.calcFee("B999", 29999);
        assertThat(actual, is(200));
    }

    @Test
    public void calcFee_ToOtherBank_Over30000_ReturnsRightFee() {
        int actual = feeService.calcFee("B999", 30000);
        assertThat(actual, is(300));
    }
}