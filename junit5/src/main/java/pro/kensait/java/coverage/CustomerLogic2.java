package pro.kensait.java.coverage;

public class CustomerLogic2 {
    public static int calcDeliveryFee(CustomerType customerType, int totalPrice) {
        int deliveryFee = 600;
        if (customerType == CustomerType.GOLD //［1］
                || 3000 <= totalPrice) { //［2］
            deliveryFee = 0; //［3］
        }
        return deliveryFee;
    }
}
