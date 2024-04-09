package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShippingServiceTest_Copilot {
    private ShippingService shippingService;
    private Client client;
    private LocalDate receiveDate;
    private List<Baggage> baggageList;

    @BeforeEach
    void setUp() {
        shippingService = new ShippingService();
        client = new Client(ClientType.DIAMOND, "John Doe", "USA");
        receiveDate = LocalDate.now().plusDays(1);
        baggageList = new ArrayList<>();
        baggageList.add(new Baggage("Luggage", 10));
        baggageList.add(new Baggage("Box", 5));
    }

    @Test
    void testOrderShippingWithDiamondClient() {
        int expectedTotalCost = 100; // Set your expected total cost here
        shippingService.orderShipping(client, receiveDate, baggageList);
        // Assert the total cost is as expected
        assertEquals(expectedTotalCost, shippingService.getTotalCost());
    }

    @Test
    void testOrderShippingWithGoldClient() {
        int expectedTotalCost = 200; // Set your expected total cost here
        client = new Client(ClientType.GOLD, "Jane Smith", "UK");
        shippingService.orderShipping(client, receiveDate, baggageList);
        // Assert the total cost is as expected
        assertEquals(expectedTotalCost, shippingService.getTotalCost());
    }
}