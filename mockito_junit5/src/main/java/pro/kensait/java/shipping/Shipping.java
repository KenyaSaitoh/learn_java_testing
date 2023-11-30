package pro.kensait.java.shipping;

import java.time.LocalDate;
import java.util.List;

/*
 * 配送データ
 */
public record Shipping (
        LocalDate orderDateTime, // 注文日
        Client client, // 顧客
        LocalDate receiveDateTime, // 受取日
        List<Baggage> baggageList, // 荷物リスト
        Integer totalPrice) { // 合計金額
}