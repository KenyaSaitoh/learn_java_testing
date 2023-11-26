package pro.kensait.java.shipping;

import java.time.LocalDateTime;
import java.util.List;

public class ShippingService {
    private static final int DIAMOND_COST_LIMIT = 3000;
    private static final float DIAMOND_NET_RATE = 0.75F;
    private static final int GOLD_COST_LIMIT = 4000;
    private static final float GOLD_NET_RATE = 0.9F;
    private CostCalculator costCalculator;

    public ShippingService(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    public void orderShipping(ShippingClient client,
            LocalDateTime receiveDateTime,
            List<Baggage> baggageList) {
        Integer totalCost = 0;
        for (Baggage baggage : baggageList) {
            Integer shippingCost =
                costCalculator.calcShippingCost(baggage.baggageType(),
                client.originRegion());
            totalCost = totalCost + shippingCost;
        }

        if (client.ClientType() == ClientType.DIAMOND) {
            if (DIAMOND_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * DIAMOND_NET_RATE));
                totalCost = discountedPrice < DIAMOND_COST_LIMIT ?
                        DIAMOND_COST_LIMIT :
                            discountedPrice;
            }
        } else if (client.ClientType() == ClientType.GOLD) {
            if (GOLD_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * GOLD_NET_RATE));
                totalCost = discountedPrice < GOLD_COST_LIMIT ?
                        GOLD_COST_LIMIT :
                            discountedPrice;
            }
        }

        Shipping shipping = new Shipping(
                LocalDateTime.now(),
                client,
                receiveDateTime,
                baggageList,
                totalCost);
        ShippingRepository.save(shipping);
    }
}
