package pro.kensait.junit5.shipping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * 配送処理を表すビジネスロジック（テスト対象）
 */
public class ShippingService {
    public static final Integer BASE_PRICE = 1000; // 配送料計算のベースとなる価格
    private static final int GOLD_COST_LIMIT = 3000; // ゴールド会員の割引後の下限金額
    private static final float GOLD_NET_RATE = 0.9F; // ゴールド会員の割引率
    private static final int DIAMOND_COST_LIMIT = 2500; // ダイヤモンド会員の割引後の下限金額
    private static final float DIAMOND_NET_RATE = 0.75F; // ダイヤモンド会員の割引率

    // 配送の注文を受ける
    public void orderShipping(Client client, LocalDate receiveDate,  List<Baggage> baggageList) {

        // 配送料の合計値
        Integer totalCost = 0;

        // 荷物リストでループし、一つ一つの荷物種別ごとに配送料を計算
        // → それらを集計し、配送料の合計値を算出する
        for (Baggage baggage : baggageList) {
            Integer shippingCost =
                calcShippingCost(baggage.baggageType(), client.originRegion());
            totalCost = totalCost + shippingCost;
        }

        // ゴールド会員の場合は、ゴールド会員用の割引率を適用する
        // → ただし定義された「割引後の下限金額」を下回ることは許容されない
        if (client.clientType() == ClientType.GOLD) {
            if (GOLD_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * GOLD_NET_RATE)); // 割引率0.90をかける
                totalCost = discountedPrice < GOLD_COST_LIMIT ?
                        GOLD_COST_LIMIT : // 割引後に3000円を下回る場合は、3000を返す
                            discountedPrice; // 下回らない場合は、割引後の数値を返す
            }

            // ダイヤモンド会員の場合は、ダイヤモンド会員用の割引率を適用する
        // → ただし定義された「割引後の下限金額」を下回ることは許容されない
        } else if (client.clientType() == ClientType.DIAMOND) {
            if (DIAMOND_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * DIAMOND_NET_RATE)); // 割引率0.75をかける
                totalCost = discountedPrice < DIAMOND_COST_LIMIT ?
                        DIAMOND_COST_LIMIT : // 割引後に2500円を下回る場合は、2500を返す
                            discountedPrice; // 下回らない場合は、割引後の数値を返す
            }
        }

        // 配送を表すレコードを生成する
        Shipping shipping = new Shipping(LocalDateTime.now(), client, receiveDate, baggageList,
                totalCost);

        // 配送DAOに配送レコードを保存する
        ShippingDAO.save(shipping);
    }

    /* 荷物種別と地域種別、それぞれの「重み」をもとに配送料を計算する
     * 補足：金額計算なので、本質的にはBigDecimal型を使うべきだが、テスト技法を学ぶ上では
     * ノイズになるので、ここでは便宜上Integer型を使用する
     */
    private Integer calcShippingCost(BaggageType baggageType,
            RegionType regionType) {
        Float b = baggageType.getWeighting();
        Float r = regionType.getWeighting();
        Integer shippingCost = Math.round(BASE_PRICE * b * r);
        return shippingCost;
    }
}