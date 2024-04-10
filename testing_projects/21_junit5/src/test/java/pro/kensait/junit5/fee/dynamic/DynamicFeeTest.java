package pro.kensait.junit5.fee.dynamic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import pro.kensait.junit5.fee.FeeService;

/*
 * FeeService（振込手数料計算サービス）を対象にしたテストクラス
 * 動的テストによって実行時にテストケースを動的に生成する
 */
public class DynamicFeeTest {
    private static final String OUR_BANK_CODE = "B001"; // 自分の銀行
    private static final String OTHER_BANK_CODE = "B999"; // 他の銀行

    // テスト対象クラス
    FeeService feeService;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        feeService = new FeeService();
    }

    @TestFactory
    Collection<DynamicTest> dynamicFeeTests() {
        return Arrays.asList(
                DynamicTest.dynamicTest("自分の銀行宛に30000円を振込、"
                        + "手数料が0円であることをテストする", () -> {
                    assertEquals(0, feeService.calcFee(OUR_BANK_CODE, 30000));
                }),
                DynamicTest.dynamicTest("自分の銀行宛に29999円を振込、"
                        + "手数料が100円であることをテストする", () -> {
                    assertEquals(100, feeService.calcFee(OUR_BANK_CODE, 29999));
                }),
                DynamicTest.dynamicTest("他の銀行宛に40000円を振込、"
                        + "手数料が200円であることをテストする", () -> {
                    assertEquals(200, feeService.calcFee(OTHER_BANK_CODE, 40000));
                }),
                DynamicTest.dynamicTest("他の銀行宛に39999円を振込、"
                        + "手数料が500円であることをテストする", () -> {
                    assertEquals(500, feeService.calcFee(OTHER_BANK_CODE, 39999));
                }));
    }
}