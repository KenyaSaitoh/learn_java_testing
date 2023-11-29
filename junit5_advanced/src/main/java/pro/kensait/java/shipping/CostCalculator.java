package pro.kensait.java.shipping;

/*
 * 配送料を計算するためのビジネスロジック（モック対象）
 */
public class CostCalculator implements CostCalculatorIF {
    private Integer basePrice;

    public CostCalculator(Integer basePrice) {
        super();
        this.basePrice = basePrice;
    }

    @Override
    public Integer getBasePrice() {
        return basePrice;
    }

    @Override
    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public Integer calcShippingCost(BaggageType baggageType,
            RegionType regionType) {
        // 金額計算なので、本質的にはBigDecimal型を使うべきだが、テスト技法を学ぶ上では
        // ノイズになるので、ここでは便宜上、Integer型を使用する

        Float b = baggageType.getWeighting();
        Float r = regionType.getWeighting();
        Integer shippingCost = Math.round(basePrice * b * r);
        return shippingCost;
    }
}