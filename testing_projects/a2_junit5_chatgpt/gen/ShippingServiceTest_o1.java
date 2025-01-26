package pro.kensait.java.shipping;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * ShippingServiceの単体テストクラス
 */
public class ShippingServiceTest_o1 {

    // モック対象
    @Mock
    private CostCalculatorIF costCalculator;
    @Mock
    private Baggage baggage1;
    @Mock
    private Baggage baggage2;
    @Mock
    private Client client;

    // テスト対象クラス
    private ShippingService shippingService;

    /**
     * 前処理
     * ShippingServiceのインスタンスを生成します
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shippingService = new ShippingService(costCalculator);
    }

    /**
     * GENERAL会員の場合、割引が適用されないことを確認するテスト
     */
    @Test
    void testOrderShipping_General_NoDiscount() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.GENERAL);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        // calcShippingCostをモックで返却
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU))
            .thenReturn(1000);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1));

        // 検証
        verify(costCalculator, times(1))
            .calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        // 割引関連の分岐がないことをカバレッジで確認
    }

    /**
     * GOLD会員の場合、合計金額が下限額(3000円)以下なら割引が適用されないことを確認するテスト
     */
    @Test
    void testOrderShipping_Gold_NoDiscount() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        // 合計が3000円以下になるように設定
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU))
            .thenReturn(2500);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1));

        // 検証
        verify(costCalculator, times(1))
            .calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
        // 割引が行われない分岐を通ることをカバレッジで確認
    }

    /**
     * GOLD会員の場合、合計金額が下限額(3000円)を超えると割引が適用されることを確認するテスト
     * （割引後の料金が3000円を上回るケース）
     */
    @Test
    void testOrderShipping_Gold_Discount() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(baggage2.baggageType()).thenReturn(BaggageType.SMALL);

        // 例: 2500 + 1000 = 3500円 → 割引後は3150円 (3000円を上回る)
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU))
            .thenReturn(2500);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU))
            .thenReturn(1000);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1, baggage2));

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        // 割引が適用され、割引後が3000円より大きいケースをカバレッジで確認
    }

    /**
     * GOLD会員の場合、合計金額が下限額(3000円)を超えるが、
     * 割引後の金額が3000円未満となる場合、3000円に補正されることを確認するテスト
     */
    @Test
    void testOrderShipping_Gold_DiscountLimit() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.LARGE);
        // 例: 3300円 → 3300 * 0.9 = 2970円 → 下限額3000円を下回るので3000円に補正
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU))
            .thenReturn(3300);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1));

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.LARGE, RegionType.HONSHU);
        // 割引後の金額が3000円を下回るケースをカバレッジで確認
    }

    /**
     * DIAMOND会員の場合、合計金額が下限額(2500円)以下なら割引が適用されないことを確認するテスト
     */
    @Test
    void testOrderShipping_Diamond_NoDiscount() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        // 合計が2500円以下になるように設定
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU))
            .thenReturn(2000);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1));

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        // 割引が行われない分岐を通ることをカバレッジで確認
    }

    /**
     * DIAMOND会員の場合、合計金額が下限額(2500円)を超えると割引が適用されることを確認するテスト
     * （割引後の料金が2500円を上回るケース）
     */
    @Test
    void testOrderShipping_Diamond_Discount() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(baggage2.baggageType()).thenReturn(BaggageType.SMALL);

        // 例: 3000 + 1000 = 4000円 → 4000 * 0.75 = 3000円 (2500円を上回る)
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU))
            .thenReturn(3000);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU))
            .thenReturn(1000);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1, baggage2));

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        // 割引が適用され、割引後が2500円より大きいケースをカバレッジで確認
    }

    /**
     * DIAMOND会員の場合、合計金額が下限額(2500円)を超えるが、
     * 割引後の金額が2500円未満となる場合、2500円に補正されることを確認するテスト
     */
    @Test
    void testOrderShipping_Diamond_DiscountLimit() {
        // 準備
        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.LARGE);

        // 例: 3300円 → 3300 * 0.75 = 2475円 → 下限額2500円を下回るので2500円に補正
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU))
            .thenReturn(3300);

        // 実行
        shippingService.orderShipping(client, LocalDate.now(), Arrays.asList(baggage1));

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.LARGE, RegionType.HONSHU);
        // 割引後の金額が2500円を下回るケースをカバレッジで確認
    }
}
