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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 * ShippingServiceTestとの相違点
 * 引数であるBaggage、Clientはモック化しない
 */
public class ShippingServiceTest_1 {
    // テスト対象クラス
    ShippingService shippingService;

    // テスト対象クラスの呼び出し先
    @Mock
    CostCalculatorIF costCalculator;

    //その他のすべてのテストケースで共通的なフィクスチャ
    Baggage baggage;
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    // 各テストケースで共通的な前処理
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドをモック化する）
        MockitoAnnotations.openMocks(this);

        // モック化されたCostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                nullable(BaggageType.class), nullable(RegionType.class))).thenReturn(1600);

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // 共通フィクスチャを設定する
        baggage = new Baggage(BaggageType.MIDDLE, false);
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);

        // DAOが保持するリストをクリアする（DB利用時はテーブル初期化に相当する）
        ShippingDAO.findAll().clear();
    }

    @Nested
    @DisplayName("ゴールド会員のテスト")
    class GoldCustomerTest {
        // GoldCustomerTestクラス内の各テストケースで共通的なフィクスチャ
        Client client;

        // GoldCustomerTestクラス内の各テストケースで共通的な前処理
        @BeforeEach
        void setUp() {
            // ゴールド会員のインスタンスを生成する
            client = new Client(10001, "Alice", "福岡県福岡市1-1-1",
                    ClientType.GOLD, RegionType.KYUSHU);
        }

        @Test
        @DisplayName("割引なしの場合の更新結果をテストする")
        void test_OrderShipping_GoldCustomer_NoDiscount() {
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
        // DiamondCustomerTestクラス内の各テストケースで共通的なフィクスチャ
        Client client;

        // DiamondCustomerTestクラス内の各テストケースで共通的な前処理
        @BeforeEach
        void setUp() {
            // ダイヤモンド会員のインスタンスを生成する
            client = new Client(10001, "Alice", "福岡県福岡市1-1-1",
                    ClientType.DIAMOND, RegionType.KYUSHU);
        }

        @Test
        @DisplayName("割引なしの場合の更新結果をテストする")
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