package pro.kensait.java.app.fee;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class FeeParameterTest1 {

    FeeService feeService;

    @DataPoints
    public static Fixture[] fixtureArray = {
            new Fixture("B001", 49999, 0),
            new Fixture("B001", 50000, 100),
            new Fixture("B999", 29999, 200),
            new Fixture("B999", 30000, 300)
    };
    
    @Before
    public void setUp() {
        feeService = new FeeService();
    }

    @Theory
    public void calcFee(Fixture f) {
        int actual = feeService.calcFee(f.bankCode, f.amount);
        assertThat(actual, is(f.expectedFee));
    }

    static class Fixture {
        String bankCode;
        int amount;
        int expectedFee;
        public Fixture(String bankCode, int amount, int expectedFee) {
            this.bankCode = bankCode;
            this.amount = amount;
            this.expectedFee = expectedFee;
        }
    }
}