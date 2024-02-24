package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 * @ValueSourceからアノテーションから単一のパラメータを読み込む
 */
@DisplayName("振込手数料計算サービスのテストクラス1")
public class FeeParameterTest_1 {
    // テスト対象クラス
    FeeService feeService;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @ParameterizedTest(name = "順番:{index} ; 引数:{arguments}")
    @ValueSource(strings = {"B001,30000,0", "B001,29999,100", "B999,40000,200", "B999,39999,500"})
    @DisplayName("リテラル配列からパラメータを取得するテストメソッド")
    void test_CalcFee(String paramStr) {
        String[] param = paramStr.split(",");
        // 実行フェーズ
        int actual = feeService.calcFee(param[0], Integer.parseInt(param[1]));
        // 検証フェーズ
        assertEquals(Integer.parseInt(param[2]), actual);
    }
}