package pro.kensait.junit5.customer;

/*
 * 顧客サービスを表すクラス
 */
public class CustomerService1 {

    // 顧客タイプから配送料を決定する
    public static int calcDeliveryFee(CustomerType customerType) {
        int deliveryFee = 600;
        if (customerType == CustomerType.GOLD) { //［1］
            deliveryFee = 0; //［2］
        }
        return deliveryFee;
    }
}
