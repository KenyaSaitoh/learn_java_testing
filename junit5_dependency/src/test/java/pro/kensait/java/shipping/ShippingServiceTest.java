package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class ShippingServiceTest {
    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    ShippingService shippingService;

    // 共通的な変数
    LocalDate receiveDate;
    Client diamondClient;
    Client goldClient;

    // 共通的な変数のうち、モック対象
    MockCostCalculator mockCostCalculator;

    // 変数を初期化する
    @BeforeEach
    void setUp() {
        mockCostCalculator = new MockCostCalculator();
        shippingService = new ShippingService(mockCostCalculator);
        diamondClient = new Client(10001, "Alice", "大阪府住吉区1-1-1",
                ClientType.DIAMOND, RegionType.KANSAI);
        goldClient = new Client(10001, "Alice", "大阪府住吉区1-1-1",
                ClientType.GOLD, RegionType.KANSAI);
        receiveDate = LocalDate.of(2023, 11, 30);

        // リポジトリをクリアする → これはデータベース初期化に相当する
        ShippingRepository.shippingList.clear();
    }

    @Test
    void testOrderShipping_DiamondCustomer_NoDiscount() {
        // テスト用の顧客と荷物リストを設定する
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false));

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), diamondClient, receiveDate, baggageList,
                1600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    void testOrderShipping_DiamondCustomer_Discount_ReachLimit() {
        // 荷物リストを設定する
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), diamondClient, receiveDate, baggageList,
                2500);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    void testOrderShipping_DiamondCustomer_Discount_NoLimit() {
        // 荷物リストを設定する
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.SMALL, false),
                new Baggage(BaggageType.MIDDLE, true),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), diamondClient, receiveDate, baggageList,
                3600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    void testOrderShipping_GoldCustomer_NoDiscount() {
        // テスト用の顧客と荷物リストを設定する
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false));

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), goldClient, receiveDate, baggageList,
                1600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    void testOrderShipping_GoldCustomer_Discount_ReachLimit() {
        // 荷物リストを設定する
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), goldClient, receiveDate, baggageList,
                3000);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    void testOrderShipping_GoldCustomer_Discount_NoLimit() {
        // 荷物リストを設定する
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.SMALL, false),
                new Baggage(BaggageType.MIDDLE, true),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), goldClient, receiveDate, baggageList,
                4320);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }
}