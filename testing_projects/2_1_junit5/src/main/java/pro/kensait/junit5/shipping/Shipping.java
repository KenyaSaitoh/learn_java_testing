package pro.kensait.junit5.shipping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/*
 * 配送データを表すレコードクラス
 */
public record Shipping(
        LocalDateTime orderDateTime, // 注文日時
        Client client, // 顧客
        LocalDate receiveDate, // 受取日
        List<Baggage> baggageList, // 荷物リスト
        Integer totalPrice) // 合計金額 
{
    @Override
    public int hashCode() {
        return Objects.hash(baggageList, client, receiveDate, totalPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Shipping other = (Shipping) obj;
        return Objects.equals(baggageList, other.baggageList)
                && Objects.equals(client, other.client)
                && Objects.equals(receiveDate, other.receiveDate)
                && Objects.equals(totalPrice, other.totalPrice);
    }
}