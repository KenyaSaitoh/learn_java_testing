package pro.kensait.java.app.ec.model.service;

import pro.kensait.java.app.ec.model.domain.AbstractCustomer;
import pro.kensait.java.app.ec.model.domain.AbstractTransaction;
import pro.kensait.java.app.ec.model.domain.Product;
import pro.kensait.java.app.ec.model.dto.ReturnTransactionDTO;

public class ReturnService {

    public void returnProduct(ReturnTransactionDTO returnTransactionDTO)
            throws Exception {

        // 顧客ドメインオブジェクトを取得する
        AbstractCustomer customer = null;

        // 商品ドメインオブジェクトを取得する
        Product product = null;

        // 返品取引ドメインオブジェクトを生成する
        AbstractTransaction returnTransaction = null;

        // 返送料を計算する
        returnTransaction.calcDeliveryCharge();

        // RETURN_TRANSACTIONテーブルに挿入する
        // entityManager.persist(returnTransaction);
    }
}
