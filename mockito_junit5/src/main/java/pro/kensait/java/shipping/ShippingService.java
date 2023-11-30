package pro.kensait.java.shipping;

import java.time.LocalDate;
import java.util.List;

/*
 * 配送処理を表すビジネスロジック（テスト対象）
 */
public class ShippingService {
    private static final int DIAMOND_COST_LIMIT = 2500; // ダイヤモンド会員の割引後の下限金額
    private static final float DIAMOND_NET_RATE = 0.75F; // ダイヤモンド会員の割引率
    private static final int GOLD_COST_LIMIT = 3000; // ゴールド会員の割引後の下限金額
    private static final float GOLD_NET_RATE = 0.9F; // ゴールド会員の割引率

    // 配送料計算ロジックを表すインタフェース
    private CostCalculatorIF costCalculator;

    // コンストラクタ
    public ShippingService(CostCalculatorIF costCalculator) {
        this.costCalculator = costCalculator;
    }

    // 配送の注文を受ける
    public void orderShipping(Client client,
            LocalDate receiveDate,
            List<Baggage> baggageList) {

        // 配送料の合計値
        Integer totalCost = 0;

        // 荷物リストでループし、一つ一つの荷物種別ごとに配送料を計算
        // → それらを集計し、配送料の合計値を算出する
        for (Baggage baggage : baggageList) {
            Integer shippingCost =
                costCalculator.calcShippingCost(baggage.baggageType(),
                client.originRegion());
            totalCost = totalCost + shippingCost;
        }

        // ダイヤモンド会員の場合は、ダイヤモンド会員用の割引率を適用する
        // → ただし定義された「割引後の下限金額」を下回ることは許容されない
        if (client.ClientType() == ClientType.DIAMOND) {
            if (DIAMOND_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * DIAMOND_NET_RATE));
                totalCost = discountedPrice < DIAMOND_COST_LIMIT ?
                        DIAMOND_COST_LIMIT :
                            discountedPrice;
            }

        // ゴールド会員の場合は、ゴールド会員用の割引率を適用する
        // → ただし定義された「割引後の下限金額」を下回ることは許容されない
        } else if (client.ClientType() == ClientType.GOLD) {
            if (GOLD_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * GOLD_NET_RATE));
                totalCost = discountedPrice < GOLD_COST_LIMIT ?
                        GOLD_COST_LIMIT :
                            discountedPrice;
            }
        }

        // 配送を表すレコードを生成する
        Shipping shipping = new Shipping(
                LocalDate.now(),
                client,
                receiveDate,
                baggageList,
                totalCost);

        // 配送リポジトリに配送レコードを保存する
        ShippingRepository.save(shipping);
    }
}
