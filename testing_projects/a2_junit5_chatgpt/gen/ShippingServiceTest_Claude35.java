package pro.kensait.java.shipping;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Claude3.5が生成したコード
@ExtendWith(MockitoExtension.class)
class ShippingServiceTest_Claude35 {

    private ShippingService shippingService;

    @Mock
    private CostCalculatorIF costCalculator;

    @Mock
    private Baggage baggage1;

    @Mock
    private Baggage baggage2;

    @Mock
    private Client client;

    @BeforeEach
    void setUp() {
        shippingService = new ShippingService(costCalculator);
    }

    @Test
    void testOrderShippingForGeneralClient() {
        // 一般会員の配送注文のテスト
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.GENERAL);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(1000);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1200);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
    }

    @Test
    void testOrderShippingForGoldClientWithDiscountAboveMinimum() {
        // ゴールド会員の割引適用後、下限金額を上回るケース
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.LARGE);
        when(baggage2.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(2500);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
        // 注: 合計金額が5000円で、90%割引後は4500円となり、下限金額の3000円を上回るはず
    }

    @Test
    void testOrderShippingForGoldClientWithDiscountAtMinimum() {
        // ゴールド会員の割引適用後、ちょうど下限金額になるケース
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1667);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
        // 注: 合計金額が3334円で、90%割引後はちょうど3000円（下限金額）になるはず
    }

    @Test
    void testOrderShippingForGoldClientWithDiscountBelowMinimum() {
        // ゴールド会員の割引適用後、下限金額を下回るが下限金額が適用されるケース
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(baggage2.baggageType()).thenReturn(BaggageType.SMALL);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1800);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(1500);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
        // 注: 合計金額が3300円で、90%割引後は2970円となるが、下限金額の3000円が適用されるはず
    }

    @Test
    void testOrderShippingForDiamondClientWithDiscountAboveMinimum() {
        // ダイヤモンド会員の割引適用後、下限金額を上回るケース
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.LARGE);
        when(baggage2.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(2500);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
        // 注: 合計金額が5000円で、75%割引後は3750円となり、下限金額の2500円を上回るはず
    }

    @Test
    void testOrderShippingForDiamondClientWithDiscountAtMinimum() {
        // ダイヤモンド会員の割引適用後、ちょうど下限金額になるケース
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1667);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
        // 注: 合計金額が3334円で、75%割引後はちょうど2500円（下限金額）になるはず
    }

    @Test
    void testOrderShippingForDiamondClientWithDiscountBelowMinimum() {
        // ダイヤモンド会員の割引適用後、下限金額を下回るが下限金額が適用されるケース
        LocalDate receiveDate = LocalDate.now().plusDays(3);
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(baggage2.baggageType()).thenReturn(BaggageType.SMALL);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1800);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(1500);

        shippingService.orderShipping(client, receiveDate, baggageList);

        verify(costCalculator, times(2)).calcShippingCost(any(), any());
        // 注: 合計金額が3300円で、75%割引後は2475円となるが、下限金額の2500円が適用されるはず
    }
}