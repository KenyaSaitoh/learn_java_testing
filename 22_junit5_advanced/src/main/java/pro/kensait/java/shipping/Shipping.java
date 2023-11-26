package pro.kensait.java.shipping;

import java.time.LocalDateTime;
import java.util.List;

public record Shipping (
        LocalDateTime orderDateTime,
        ShippingClient client,
        LocalDateTime receiveDateTime,
        List<Baggage> baggageList,
        Integer totalPrice) {
}