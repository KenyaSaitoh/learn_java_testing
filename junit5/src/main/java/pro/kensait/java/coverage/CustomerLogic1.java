package pro.kensait.java.coverage;

public class CustomerLogic1 {
    public static int calcDeliveryFee(CustomerType customerType) {
        int deliveryFee = 600;
        if (customerType == CustomerType.GOLD) { //［1］
            deliveryFee = 0; //［2］
        }
        return deliveryFee;
    }
}
