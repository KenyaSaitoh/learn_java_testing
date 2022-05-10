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

    @DataPoints
    public static List<Fixture> getParamListFromCSV() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (
                InputStream is = classLoader.getResourceAsStream("parameter.csv");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"))) {
            List<Fixture> paramList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                Fixture p = createFixtureFromLine(line);
                paramList.add(p);
            }
            return paramList;
        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static Fixture createFixtureFromLine(String line) {
        StringTokenizer st = new StringTokenizer(line, ",");
        String bankCode = st.nextToken();
        int amount = Integer.parseInt(st.nextToken());
        int expectedFee = Integer.parseInt(st.nextToken());
        Fixture p = new Fixture(bankCode, amount, expectedFee);
        return p;
    }
}