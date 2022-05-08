package pro.kensait.java.app.fee;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class FeeEnclosedTest2 {

    public static class 自行宛振込 {
        FeeService feeService;

        @Before
        public void setUp() {
            feeService = new FeeService();
        }

        @Test
        // 50000円から始まるメソッド名はNG
        public void 金額50000円未満を振り込む手数料を計算する() {
            int actual = feeService.calcFee("B001", 49999);
            assertThat(0, is(actual));
        }

        @Test
        public void 金額50000円以上を振り込む手数料を計算する() {
            int actual = feeService.calcFee("B001", 50000);
            assertThat(100, is(actual));
        }

    }

    public static class 他行宛振込 {
        FeeService feeService;

        @Before
        public void setUp() {
            feeService = new FeeService();
        }

        @Test
        public void 金額30000円未満を振り込む手数料を計算する() {
            int actual = feeService.calcFee("B009", 29999);
            assertThat(200, is(actual));
        }

        @Test
        public void 金額30000円以上を振り込む手数料を計算する() {
            int actual = feeService.calcFee("B009", 30000);
            assertThat(300, is(actual));
        }
    }
}