package pro.kensait.java.shipping;

/*
 * 荷物毎の配送料計算ロジックを表すクラス（モック対象）
 */
public class CostCalculator implements CostCalculatorIF {
    @Override
    public Integer calcShippingCost(BaggageType baggageType,
            RegionType regionType) {
        /* 
         * 荷物種別を表す列挙型と発送元の地域種別を表す列挙型、それぞれの「重み」をもとに荷物毎の配送料を計算する
         * 補足：金額計算なので、本質的にはBigDecimal型を使うべきだが、テスト技法を学ぶ上では
         * ノイズになるので、ここでは便宜上Integer型を使用する
         */
        Float b = baggageType.getWeighting();
        Float r = regionType.getWeighting();
        Integer shippingCost = Math.round(BASE_PRICE * b * r);
        return shippingCost;
    }
}