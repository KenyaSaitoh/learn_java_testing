package pro.kensait.java.shipping;

/*
 * 配送料計算ロジックを表すインタフェース
 */
public interface CostCalculatorIF {
    // 配送料計算のベースとなる価格
    public static final Integer BASE_PRICE = 1000;

    // 配送料金を計算するメソッド
    Integer calcShippingCost(BaggageType baggageType, RegionType regionType);
}