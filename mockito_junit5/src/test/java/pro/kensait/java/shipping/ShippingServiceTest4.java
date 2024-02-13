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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * ShippingServiceを対象にしたテストクラス
 * ShippingServiceTest3との相違点
 * openMocks()の代わりに、@ExtendWith(MockitoExtension.class)を指定する点
 */
@ExtendWith(MockitoExtension.class)
public class ShippingServiceTest4 {
    // テスト対象クラス
    ShippingService shippingService;

    // テスト対象クラスの呼び出し先（@Mockを付与してモック化）
    @Mock CostCalculatorIF costCalculator;

    // 各テストケースで共通的なフィクスチャ（@Mockを付与してモック化）［差分］
    @Mock Baggage baggage;

    // その他のモック化対象外の共通的なフィクスチャ
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    // 各テストケースで共通的な事前処理
    @BeforeEach
    void setUp() {
        // モック化されたCostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                nullable(BaggageType.class), nullable(RegionType.class))).thenReturn(1600);

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // モック化されたBaggageの振る舞いを決める［差分］
        when(baggage.baggageType()).thenReturn(BaggageType.MIDDLE);

        // その他の共通的なフィクスチャを設定する
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);

        // DAOが保持するリストをクリアする（DB利用時はテーブル初期化に相当する）
        ShippingDAO.findAll().clear();
    }

    @Nested
    @DisplayName("ゴールド会員のテスト")
    class GoldCustomerTest {
        // GoldCustomerTestクラス内の各テストケースで共通的なフィクスチャ［差分］
        @Mock Client client;

        // GoldCustomerTestクラス内の各テストケースで共通的な事前処理
        @BeforeEach
        void setUp() {
            // モック化されたClientの振る舞いを決める［差分］
            when(client.clientType()).thenReturn(ClientType.GOLD);
        }

        @Test
        @DisplayName("割引なしになった場合の更新結果をテストする")
        void test_OrderShipping_GoldCustomer_NoDiscount() {
            // 引数である荷物リストを生成する（テストケース毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage);

            // テスト実行
            shippingService.orderShipping(client, receiveDate, baggageList);

            // DAOが保持するリストから実測値を取得する
            Shipping actual = ShippingDAO.findAll().get(0);

            // 期待値を生成する
            Shipping expected = new Shipping(orderDateTime, client, receiveDate,
                    baggageList, 1600);

            // 期待値と実測値が一致しているかを検証する
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("割引になった場合（ただし下限に到達）の更新結果をテストする")
        void test_OrderShipping_GoldCustomer_Discount_ReachLimit() {
            // 引数である荷物リストを生成する（テストケース毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage, baggage);

            // テスト実行
            shippingService.orderShipping(client, receiveDate, baggageList);

            // DAOが保持するリストから実測値を取得する
            Shipping actual = ShippingDAO.findAll().get(0);

            // 期待値を生成する
            Shipping expected = new Shipping(orderDateTime, client, receiveDate,
                    baggageList, 3000);

            // 期待値と実測値が一致しているかを検証する
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("割引になった場合（下限に到達せず）の更新結果をテストする")
        void test_OrderShipping_GoldCustomer_Discount_NoLimit() {
            // 引数である荷物リストを生成する（テストケース毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage, baggage, baggage);

            // テスト実行
            shippingService.orderShipping(client, receiveDate, baggageList);

            // DAOが保持するリストから実測値を取得する
            Shipping actual = ShippingDAO.findAll().get(0);

            // 期待値を生成する
            Shipping expected = new Shipping(orderDateTime, client, receiveDate,
                    baggageList, 4320);

            // 期待値と実測値が一致しているかを検証する
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("ダイヤモンド会員のテスト")
    class DiamondCustomerTest {
        // DiamondCustomerTestクラス内の各テストケースで共通的なフィクスチャ［差分］
        @Mock Client client;

        // DiamondCustomerTestクラス内の各テストケースで共通的な事前処理
        @BeforeEach
        void setUp() {
            // モック化されたClientの振る舞いを決める［差分］
            when(client.clientType()).thenReturn(ClientType.DIAMOND);
        }

        @Test
        @DisplayName("割引なしになった場合の更新結果をテストする")
        void test_OrderShipping_DiamondCustomer_NoDiscount() {
            // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage);

            // テスト実行
            shippingService.orderShipping(client, receiveDate, baggageList);

            // DAOが保持するリストから実測値を取得する
            Shipping actual = ShippingDAO.findAll().get(0);

            // 期待値を生成する
            Shipping expected = new Shipping(orderDateTime, client, receiveDate,
                    baggageList, 1600);

        // 期待値と実測値が一致しているかを検証する
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("割引になった場合（ただし下限に到達）の更新結果をテストする")
        void test_OrderShipping_DiamondCustomer_Discount_ReachLimit() {
            // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage, baggage);

            // テスト実行
            shippingService.orderShipping(client, receiveDate, baggageList);

            // DAOが保持するリストから実測値を取得する
            Shipping actual = ShippingDAO.findAll().get(0);

            // 期待値を生成する
            Shipping expected = new Shipping(orderDateTime, client, receiveDate,
                    baggageList, 2500);

            // 期待値と実測値が一致しているかを検証する
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("割引になった場合（下限に到達せず）の更新結果をテストする")
        void test_OrderShipping_DiamondCustomer_Discount_NoLimit() {
            // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage, baggage, baggage);

            // テスト実行
            shippingService.orderShipping(client, receiveDate, baggageList);

            // DAOが保持するリストから実測値を取得する
            Shipping actual = ShippingDAO.findAll().get(0);

            // 期待値を生成する
            Shipping expected = new Shipping(orderDateTime, client, receiveDate,
                    baggageList, 3600);

            // 期待値と実測値が一致しているかを検証する
            assertEquals(expected, actual);
        }
    }
}