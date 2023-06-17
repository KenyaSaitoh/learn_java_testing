package pro.kensait.java.app.fee;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FeeServiceTest {

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