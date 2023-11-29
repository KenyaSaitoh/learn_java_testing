package pro.kensait.java.shipping;

import java.time.LocalDateTime;
import java.util.List;

/*
 * 配送データ
 */
public record Shipping (
        LocalDateTime orderDateTime,
        Client client,
        LocalDateTime receiveDateTime,
        List<Baggage> baggageList,
        Integer totalPrice) {
}