package pro.kensait.java.app.ec.model.service;

import java.util.List;

import pro.kensait.java.app.ec.model.domain.AbstractCustomer;
import pro.kensait.java.app.ec.model.domain.AbstractTransaction;
import pro.kensait.java.app.ec.model.domain.OrderTransaction;
import pro.kensait.java.app.ec.model.dto.OrderTransactionDTO;

public class OrderService {

    // 注文する
    public void placeOrder(OrderTransactionDTO orderTransactionDTO)
            throws Exception {

        // 顧客ドメインオブジェクトを取得する
        Integer customerId = orderTransactionDTO.getCustomerId();
        AbstractCustomer customer = null;
        // Customer customer = entityManager.find(Customer.class, customerId);

        // 注文取引ドメインオブジェクトを生成する
        AbstractTransaction orderTransaction = null;
        /*
        OrderTransaction orderTransaction = new OrderTransaction(customer,
                orderTransactionDTO.getOrderDate()
        */

        // 限度額チェックを行う
        customer.checkTotalPriceLimit(orderTransactionDTO.getTotalPrice());

        // ポイントを計算して加算する
        Integer point = orderTransactionDTO.getTotalPrice() / 10;
        customer.addPoint(point);

        // CUSTOMERテーブル(ポイントのみ)を更新する
        // entityManager.merge(customer);

        // 送料を計算する
        orderTransaction.calcDeliveryCharge();

        // ORDER_TRANSACTIONテーブルに挿入する
        // entityManager.persist(orderTransaction);
    }
}
