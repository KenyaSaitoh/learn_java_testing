package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // 共通的な変数（テスト対象クラスの引数）
    LocalDate receiveDate;
    Client diamondClient;
    Client goldClient;

    // テスト対象クラスが依存しているクラス（モック対象）
    MockCostCalculator costCalculator;

    /*
     *  各テストメソッド呼び出しの前処理（共通変数の初期化など）
     */
    @BeforeEach
    void setUp() {
        // モックを生成する
        costCalculator = new MockCostCalculator();

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // 変数を設定する
        diamondClient = new Client(10001, "Alice", "大阪府住吉区1-1-1",
                ClientType.DIAMOND, RegionType.KANSAI);
        goldClient = new Client(10001, "Alice", "大阪府住吉区1-1-1",
                ClientType.GOLD, RegionType.KANSAI);
        receiveDate = LocalDate.of(2023, 11, 30);

        // リポジトリをクリアする → これはデータベース初期化に相当する
        ShippingRepository.shippingList.clear();
    }

    /*
     * ゴールド会員で、割引なしになった場合の更新結果をテストする
     */
    @Test
    void testOrderShipping_GoldCustomer_NoDiscount() {
        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false));

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDateTime.now(),goldClient, receiveDate,
                baggageList, 1600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    /*
     * ゴールド会員で、割引になった場合（ただし下限に到達）の更新結果をテストする
     */
    @Test
    void testOrderShipping_GoldCustomer_Discount_ReachLimit() {
        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDateTime.now(), goldClient, receiveDate,
                baggageList, 3000);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    /*
     * ゴールド会員で、割引になった場合（下限に到達せず）の更新結果をテストする
     */
    @Test
    void testOrderShipping_GoldCustomer_Discount_NoLimit() {
        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.SMALL, false),
                new Baggage(BaggageType.MIDDLE, true),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(goldClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDateTime.now(), goldClient, receiveDate,
                baggageList, 4320);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    /*
     * ダイヤモンド会員で、割引なしになった場合の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_NoDiscount() {
        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(new Baggage(BaggageType.MIDDLE, false));

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDateTime.now(), diamondClient, receiveDate,
                baggageList, 1600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    /*
     * ダイヤモンド会員で、割引になった場合（ただし下限に到達）の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_Discount_ReachLimit() {
        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDateTime.now(), diamondClient, receiveDate,
                baggageList, 2500);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    /*
     * ダイヤモンド会員で、割引になった場合（下限に到達せず）の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_Discount_NoLimit() {
        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.SMALL, false),
                new Baggage(BaggageType.MIDDLE, true),
                new Baggage(BaggageType.LARGE, true));

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDateTime.now(), diamondClient, receiveDate,
                baggageList, 3600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }
}