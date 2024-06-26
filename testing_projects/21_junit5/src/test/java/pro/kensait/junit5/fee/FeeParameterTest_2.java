package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 * @CsvFileSourceに指定されたCSVファイルから、パラメータを読み込む
 */
@DisplayName("振込手数料計算サービスのテストクラス2")
public class FeeParameterTest_2 {

    @ParameterizedTest(name = "順番:{index} ; 引数:{arguments}")
    @CsvFileSource(resources = "/parameter.csv", numLinesToSkip = 1)
    @DisplayName("CSVファイルからパラメータを取得するテストメソッド")
    void test_CalcFee(String bankCode, int amount, int expectedFee) {
        FeeService feeService = new FeeService();
        int actualFee = feeService.calcFee(bankCode, amount);
        assertEquals(expectedFee, actualFee);
    }
}