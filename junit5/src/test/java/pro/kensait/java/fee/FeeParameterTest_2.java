package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class FeeParameterTest_2 {

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
    @CsvFileSource(resources = "/parameter.csv", numLinesToSkip = 1)
    public void test_CalcFee(String bankCode, int amount, int expectedFee) {
        int actual = feeService.calcFee(bankCode, amount);
        assertEquals(expectedFee, actual);
    }
}