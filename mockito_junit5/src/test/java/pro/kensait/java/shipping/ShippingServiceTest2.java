package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 * 引数もモッキングする
 */
public class ShippingServiceTest2 {
    /*
     *  すべてのテストメソッドに共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    ShippingService shippingService;

    // テスト対象クラスの呼び出し先（@Mockを付与してモック化）
    @Mock CostCalculatorIF costCalculator;

    // 各テストメソッドで共通的なフィクスチャ（@Mockを付与してモック化）
    @Mock
    Client goldClient;
    @Mock
    Client diamondClient;
    @Mock
    Baggage baggage;

    // モック化対象外の共通的なフィクスチャ
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    /*
     *  各テストメソッド呼び出しの事前処理
     */
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        MockitoAnnotations.openMocks(this);

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // 各テストメソッドで共通的なフィクスチャをモックとして設定する
        when(goldClient.clientType()).thenReturn(ClientType.GOLD);
        when(diamondClient.clientType()).thenReturn(ClientType.DIAMOND);
        when(baggage.baggageType()).thenReturn(BaggageType.MIDDLE);

        // その他の共通的なフィクスチャを設定する
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);

        // DAOが保持するリストをクリアする（DB利用時はテーブル初期化に相当する）
        ShippingDAO.findAll().clear();
    }

    @Test
    @DisplayName("ダイヤモンド会員で、割引になった場合（下限に到達せず）の更新結果をテストする")
    void test_OrderShipping_DiamondCustomer_Discount_NoLimit() {
        // モック化されたCostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(any(), any())).thenReturn(1600);

        // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
        List<Baggage> baggageList = Arrays.asList(baggage, baggage, baggage);

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // DAOが保持するリストから実測値を取得する
        Shipping actual = ShippingDAO.findAll().get(0);

        // 期待値を生成する
        Shipping expected = new Shipping(orderDateTime, diamondClient, receiveDate,
                baggageList, 3600);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expected, actual);
    }
}