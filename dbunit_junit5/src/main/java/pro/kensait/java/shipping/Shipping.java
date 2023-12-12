package pro.kensait.java.shipping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * 配送データ
 */
public record Shipping (
        LocalDateTime orderDateTime, // 注文日時
        Client client, // 顧客
        LocalDate receiveDate, // 受取日
        List<Baggage> baggageList, // 荷物リスト
        Integer totalPrice) { // 合計金額
}