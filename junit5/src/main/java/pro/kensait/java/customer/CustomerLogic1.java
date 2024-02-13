package pro.kensait.java.customer;

public class CustomerLogic1 {
    // 顧客タイプから配送料を決定する
    public static int calcDeliveryFee(CustomerType customerType) {
        int deliveryFee = 600;
        if (customerType == CustomerType.GOLD) { //［1］
            deliveryFee = 0; //［2］
        }
        return deliveryFee;
    }
}
