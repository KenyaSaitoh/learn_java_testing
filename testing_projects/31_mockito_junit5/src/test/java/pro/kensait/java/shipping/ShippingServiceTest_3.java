package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;
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

public class ShippingServiceTest_3 {

    private ShippingService shippingService;

    @Mock
    private CostCalculatorIF costCalculator;

    @Mock
    private Client client;

    @Mock
    private Baggage baggage;

    private LocalDate receiveDate;
    private LocalDateTime orderDateTime;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shippingService = new ShippingService(costCalculator);
        receiveDate = LocalDate.now();
        orderDateTime = LocalDateTime.now();
    }

    @Test
    @DisplayName("割引なしの場合の更新をテストする")
    void test_OrderShipping_NoDiscount() {
        // Arrange
        when(client.clientType()).thenReturn(ClientType.GENERAL);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage.baggageType()).thenReturn(BaggageType.MIDDLE);
        when(costCalculator.calcShippingCost(BaggageType.MIDDLE, RegionType.HONSHU)).thenReturn(1600);

        List<Baggage> baggageList = Arrays.asList(baggage);

        // Act
        shippingService.orderShipping(client, receiveDate, baggageList);

        // Assert
        Shipping actual = ShippingDAO.findAll().get(0);
        Shipping expected = new Shipping(orderDateTime, client, receiveDate, baggageList, 1600);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("割引になった場合（ただし下限に到達）の更新をテストする")
    void test_OrderShipping_Discount_Applied_To_Limit() {
        // Arrange
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(3200);

        List<Baggage> baggageList = Arrays.asList(baggage, baggage);

        // Act
        shippingService.orderShipping(client, receiveDate, baggageList);

        // Assert
        Shipping actual = ShippingDAO.findAll().get(0);
        // 割引率 0.9 を適用し、下限の3000円に到達
        Shipping expected = new Shipping(orderDateTime, client, receiveDate, baggageList, 3000);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("割引になった場合（下限に到達せず）の更新をテストする")
    void test_OrderShipping_Discount_Applied_Not_To_Limit() {
        // Arrange
        when(client.clientType()).thenReturn(ClientType.GOLD);
        when(client.originRegion()).thenReturn(RegionType.HONSHU);
        when(baggage.baggageType()).thenReturn(BaggageType.LARGE);
        when(costCalculator.calcShippingCost(BaggageType.LARGE, RegionType.HONSHU)).thenReturn(4000);

        List<Baggage> baggageList = Arrays.asList(baggage);

        // Act
        shippingService.orderShipping(client, receiveDate, baggageList);

        // Assert
        Shipping actual = ShippingDAO.findAll().get(0);
        // 割引率 0.9 を適用し、下限の3000円を超えるが下限に到達せず
        Shipping expected = new Shipping(orderDateTime, client, receiveDate, baggageList, 3600);
        assertEquals(expected, actual);
    }
}
