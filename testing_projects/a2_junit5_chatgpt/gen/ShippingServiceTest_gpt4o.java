package pro.kensait.java.shipping;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * ShippingServiceの単体テストクラス
 */
public class ShippingServiceTest_gpt4o {
    private ShippingService shippingService;

    @Mock
    private CostCalculatorIF costCalculator;

    @Mock
    private Client client;

    @Mock
    private Baggage baggage1;

    @Mock
    private Baggage baggage2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shippingService = new ShippingService(costCalculator);
    }

    @Test
    public void testOrderShipping_GeneralClient() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.GENERAL);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(1000);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(1500);
        
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.LARGE, RegionType.HONSHU);
    }

    @Test
    public void testOrderShipping_GoldClient_DiscountApplied() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(2000);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(1500);
        
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.LARGE, RegionType.HONSHU);
    }

    @Test
    public void testOrderShipping_DiamondClient_DiscountApplied() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(3000);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(2000);
        
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.LARGE, RegionType.HONSHU);
    }

    @Test
    public void testOrderShipping_GoldClient_DiscountLimitApplied() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(1500);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(2000);
        
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
    }

    @Test
    public void testOrderShipping_DiamondClient_DiscountLimitApplied() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(2000);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1500);
        
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
    }

    @Test
    public void testOrderShipping_GoldClient_DiscountAppliedButNotBelowLimit() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(2500);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1000);
        
        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
    }

    @Test
    public void testOrderShipping_DiamondClient_DiscountAppliedButNotBelowLimit() {
        // 前提条件
        when(client.clientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage1.baggageType()).thenReturn(BaggageType.SMALL);
        when(baggage2.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.SMALL, RegionType.HONSHU)).thenReturn(3000);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1000);

        List<Baggage> baggageList = Arrays.asList(baggage1, baggage2);

        // テスト実行
        shippingService.orderShipping(client, LocalDate.now(), baggageList);

        // 検証
        verify(costCalculator).calcShippingCost(BaggageType.SMALL, RegionType.HONSHU);
        verify(costCalculator).calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU);
    }
}
