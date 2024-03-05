package pro.kensait.mockito.customer;

/*
 * 顧客サービスを表すクラス（状態を保持する）
 */
public class CustomerService {

    // 顧客タイプから配送料を決定する
    public Integer calcDeliveryFee(Customer customer) {
        System.out.println(customer);
        // 引数customerのnullチェックは行っていない
        Integer deliveryFee = 600;
        if (customer.getCustomerType() == CustomerType.GOLD) { //【1】
            deliveryFee = 0; //【2】
        }
        return deliveryFee;
    }
}
