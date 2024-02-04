package pro.kensait.java.shipping;

/*
 * 配送料を計算するためのビジネスロジック（モック対象）
 */
public class CostCalculator implements CostCalculatorIF {
    @Override
    public Integer calcShippingCost(BaggageType baggageType,
            RegionType regionType) {
        Float b = baggageType.getWeighting();
        Float r = regionType.getWeighting();
        Integer shippingCost = Math.round(BASE_PRICE * b * r);
        return shippingCost;

        /* 補足
         * 金額計算なので、本質的にはBigDecimal型を使うべきだが、テスト技法を学ぶ上では
         * ノイズになるので、ここでは便宜上Integer型を使用する
         */
    }
}