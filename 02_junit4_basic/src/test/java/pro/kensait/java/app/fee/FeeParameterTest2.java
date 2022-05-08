package pro.kensait.java.app.fee;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class FeeParameterTest2 {

    FeeService feeService;

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

    @DataPoints
    public static List<Parameter> getParamListFromCSV() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (
                InputStream is = classLoader.getResourceAsStream("parameter.csv");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"))) {
            List<Parameter> paramList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                Parameter p = createParameterFromLine(line);
                paramList.add(p);
            }
            return paramList;
        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static Parameter createParameterFromLine(String line) {
        StringTokenizer st = new StringTokenizer(line, ",");
        String bankCode = st.nextToken();
        int amount = Integer.parseInt(st.nextToken());
        int expectedFee = Integer.parseInt(st.nextToken());
        Parameter p = new Parameter(bankCode, amount, expectedFee);
        return p;
    }
}