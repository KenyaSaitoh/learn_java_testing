package pro.kensait.java.app.fee;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class FeeOurBankTest {

    FeeService feeService;

    @Before
    public void setUp() {
        feeService = new FeeService();
    }

    @Test
    public void calcFee_ToOurBank_Under50000_ReturnsRightFee() {
        int actual = feeService.calcFee("B001", 49999);
        assertThat(actual, is(0));
    }

    @Test
    public void calcFee_ToOurBank_Over50000_ReturnsRightFee() {
        int actual = feeService.calcFee("B001", 50000);
        assertThat(actual, is(100));
    } 
}