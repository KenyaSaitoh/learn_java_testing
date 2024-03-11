package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 * @CsvFileSourceに指定されたCSVファイルから、パラメータを読み込む
 * その時、アサンプションによってパラメータをフィルタリングする
 */
@DisplayName("振込手数料計算サービスのテストクラス4")
public class FeeParameterTest_4 {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行

    @ParameterizedTest(name = "順番:{index} ; 引数:{arguments}")
    @CsvFileSource(resources = "/parameter.csv", numLinesToSkip = 1)
    @DisplayName("CSVファイルからパラメータを取得するテストメソッド")
    void test_CalcFee(String bankCode, int amount, int expectedFee) {
        // アサンプションによって、パラメータとして与えられる銀行コードを、
        // 「自分の銀行」にフィルタリングする
        assumeTrue(bankCode.equals(OUR_BANK_CODE));

        // テスト続行
        FeeService feeService = new FeeService();
        int actualFee = feeService.calcFee(bankCode, amount);
        assertEquals(expectedFee, actualFee);
    }
}