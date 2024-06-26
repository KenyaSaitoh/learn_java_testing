package pro.kensait.junit5.shipping;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * ShippingServiceを対象にしたテストクラス
 * ライフサイクルとテストフィクスチャの関係性を説明するために使用
 */
public class ShippingServiceTest {
    // テスト対象クラス
    ShippingService shippingService;

    // その他の各テストメソッドで共通的なテストフィクスチャ
    Client goldClient;
    Baggage baggage;
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // 共通フィクスチャを設定する
        shippingService = new ShippingService();
        goldClient = new Client(10001, "Alice", "福岡県福岡市1-1-1",
                ClientType.GOLD, RegionType.KYUSHU);
        baggage = new Baggage(BaggageType.MIDDLE, false);
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);

        // DAOが保持するリストをクリアする（DB利用時はテーブル初期化に相当する）
        ShippingDAO.findAll().clear();
    }

    @Test
    @DisplayName("ゴールド会員で、割引なしの場合の更新をテストする")
    void test_OrderShipping_GoldCustomer_NoDiscount() {
        // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
        List<Baggage> baggageList = Arrays.asList(baggage);

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // DAOが保持するリストから実測値を取得する
        Shipping actual = ShippingDAO.findAll().get(0);

        // 期待値を生成する
        Shipping expected = new Shipping(orderDateTime, goldClient, receiveDate,
                baggageList, 1560);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ゴールド会員で、割引になった場合（ただし下限に到達）の更新をテストする")
    void test_OrderShipping_GoldCustomer_Discount_ReachLimit() {
        // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
        List<Baggage> baggageList = Arrays.asList(baggage, baggage);

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // DAOが保持するリストから実測値を取得する
        Shipping actual = ShippingDAO.findAll().get(0);

        // 期待値を生成する
        Shipping expected = new Shipping(orderDateTime, goldClient, receiveDate,
                baggageList, 3000);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ゴールド会員で、割引になった場合（下限に到達せず）の更新をテストする")
    void test_OrderShipping_GoldCustomer_Discount_NoLimit() {
        // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
        List<Baggage> baggageList = Arrays.asList(baggage, baggage, baggage);

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // DAOが保持するリストから実測値を取得する
        Shipping actual = ShippingDAO.findAll().get(0);

        // 期待値を生成する
        Shipping expected = new Shipping(orderDateTime, goldClient, receiveDate,
                baggageList, 4212);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expected, actual);
    }
}