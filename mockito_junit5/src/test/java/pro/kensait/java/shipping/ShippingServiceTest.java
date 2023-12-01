package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    // 共通的な変数のうち、モック対象（@Mockを付与）
    @Mock CostCalculatorIF costCalculator;

    // 変数を初期化する
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        MockitoAnnotations.openMocks(this);

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
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = List.of(new Baggage(BaggageType.MIDDLE, false));

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

    /*
     * ゴールド会員で、割引になった場合（ただし下限に到達）の更新結果をテストする
     */
    @Test
    void testOrderShipping_GoldCustomer_Discount_ReachLimit() {
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.MIDDLE, false));

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

    /*
     * ゴールド会員で、割引になった場合（下限に到達せず）の更新結果をテストする
     */
    @Test
    void testOrderShipping_GoldCustomer_Discount_NoLimit() {
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.MIDDLE, false));

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

    /*
     * ダイヤモンド会員で、割引なしになった場合の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_NoDiscount() {
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = List.of(new Baggage(BaggageType.MIDDLE, false));

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

    /*
     * ダイヤモンド会員で、割引になった場合（ただし下限に到達）の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_Discount_ReachLimit() {
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.MIDDLE, false));

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

    /*
     * ダイヤモンド会員で、割引になった場合（下限に到達せず）の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_Discount_NoLimit() {
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に異なる）
        List<Baggage> baggageList = Arrays.asList(
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.MIDDLE, false),
                new Baggage(BaggageType.MIDDLE, false));

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
}