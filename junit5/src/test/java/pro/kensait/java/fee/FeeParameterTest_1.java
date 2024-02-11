package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class FeeParameterTest_1 {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    /*
     *  すべてのテストメソッドに共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    FeeService feeService;

    /*
     *  各テストメソッド呼び出しの事前処理
     */
    @BeforeEach
    public void setUp() {
        feeService = new FeeService();
    }

    @ParameterizedTest
    @MethodSource("fixtureProvider")
    public void test_CalcFee(Fixture f) {
        int actual = feeService.calcFee(f.bankCode, f.amount);
        assertEquals(f.expectedFee, actual);
    }

    static Stream<Fixture> fixtureProvider() {
        return Stream.of(
            new Fixture(OUR_BANK_CODE, 30999, 0),
            new Fixture(OUR_BANK_CODE, 29000, 100),
            new Fixture(OTHER_BANK_CODE, 40000, 200),
            new Fixture(OTHER_BANK_CODE, 39999, 500)
        );
    }

    static class Fixture {
        String bankCode;
        int amount;
        int expectedFee;
        Fixture(String bankCode, int amount, int expectedFee) {
            this.bankCode = bankCode;
            this.amount = amount;
            this.expectedFee = expectedFee;
        }
    }
}