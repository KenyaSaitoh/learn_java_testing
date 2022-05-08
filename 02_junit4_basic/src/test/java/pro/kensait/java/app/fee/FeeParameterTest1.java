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
    public static Parameter[] paramArray = {
            new Parameter("B001", 49999, 0),
            new Parameter("B001", 50000, 100),
            new Parameter("B009", 29999, 200),
            new Parameter("B009", 30000, 300)
    };
    
    @Before
    public void setUp() {
        feeService = new FeeService();
    }

    @Theory
    public void calcFee(Parameter p) {
        int actual = feeService.calcFee(p.bankCode, p.amount);
        assertThat(actual, is(p.expectedFee));
    }

    static class Parameter {
        String bankCode;
        int amount;
        int expectedFee;
        public Parameter(String bankCode, int amount, int expectedFee) {
            this.bankCode = bankCode;
            this.amount = amount;
            this.expectedFee = expectedFee;
        }
    }
}