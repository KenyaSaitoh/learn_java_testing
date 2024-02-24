package pro.kensait.junit5.fee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/*
 * FeeService（手数料計算サービス）を対象にしたテストクラス
 * アノテーションから単一のパラメータを読み込む
 */
public class FeeParameterTest_1 {
    // テスト対象クラス
    FeeService feeService;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @ParameterizedTest
    @ValueSource(strings = {"B001,30999,0", "B001,29000,100", "B999,40000,200", "B999,39999,500"})
    @DisplayName("手数料計算のテスト（値リストからパラメータを取得する）")
    void test_CalcFee(String fixture) {
        String[] params = fixture.split(",");
        // 実行フェーズ
        int actual = feeService.calcFee(params[0], Integer.parseInt(params[1]));
        // 検証フェーズ
        assertEquals(Integer.parseInt(params[2]), actual);
    }
}