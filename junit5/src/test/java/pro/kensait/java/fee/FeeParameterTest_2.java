package pro.kensait.java.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import pro.kensait.java.fee.FeeService;

public class FeeParameterTest_2 {

    /*
     *  各テストケースで共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    FeeService feeService;

    /*
     *  各テストケースで共通的な事前処理
     */
    @BeforeEach
    public void setUp() {
        // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/parameter.csv", numLinesToSkip = 1)
    public void test_CalcFee(String bankCode, int amount, int expectedFee) {
        // 実行フェーズ
        int actual = feeService.calcFee(bankCode, amount);
        // 検証フェーズ
        assertEquals(expectedFee, actual);
    }
}