package pro.kensait.java.shipping;

/*
 * CostCalculatorIFをimplementsしたモック
 */
public class MockCostCalculator implements CostCalculatorIF {

    @Override
    public Integer calcShippingCost(BaggageType baggageType,
            RegionType regionType) {
        return 1600; // 疑似的に常に1600を返す
    }
}
