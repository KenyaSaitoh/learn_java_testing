package pro.kensait.java.shipping;

public class MockCostCalculator implements CostCalculatorIF {

    @Override
    public Integer calcShippingCost(BaggageType baggageType,
            RegionType regionType) {
        return 1600;
    }
}
