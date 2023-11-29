package pro.kensait.java.shipping;

/*
 * 配送料計算ロジックを表すインタフェース
 */
public interface CostCalculatorIF {
    // ベースプライスを取得するメソッド
    Integer getBasePrice();

    // ベースプライスを設定するメソッド
    void setBasePrice(Integer basePrice);

    // 配送料金を計算するメソッド
    Integer calcShippingCost(BaggageType baggageType, RegionType regionType);
}
