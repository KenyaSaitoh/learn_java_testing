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
        assertThat(0, is(actual));
    }

    @Test
    public void calcFee_ToOurBank_Over50000_ReturnsRightFee() {
        int actual = feeService.calcFee("B001", 50000);
        assertThat(100, is(actual));
    } 

    @Test
    public void calcFee_ToOtherBank_Under30000_ReturnsRightFee() {
        int actual = feeService.calcFee("B009", 29999);
        assertThat(200, is(actual));
    }

    @Test
    public void calcFee_ToOtherBank_Over30000_ReturnsRightFee() {
        int actual = feeService.calcFee("B009", 30000);
        assertThat(300, is(actual));
    }
}