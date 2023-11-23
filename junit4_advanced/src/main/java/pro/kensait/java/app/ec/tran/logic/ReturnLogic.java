package pro.kensait.java.app.ec.tran.logic;

import pro.kensait.java.app.ec.tran.dto.CustomerDTO;
import pro.kensait.java.app.ec.tran.dto.OrderDetailDTO;
import pro.kensait.java.app.ec.tran.dto.OrderTransactionDTO;
import pro.kensait.java.app.ec.tran.dto.ProductDTO;
import pro.kensait.java.app.ec.tran.dto.ReturnTransactionDTO;
import pro.kensait.java.app.ec.tran.util.Util;

public class ReturnLogic {

    public void returnProduct(ReturnTransactionDTO returnTransaction) {

        // 顧客オブジェクトを取得する
        CustomerDTO customer = null;

        // 商品オブジェクトを取得する
        ProductDTO product = null;

        // 返送料を計算する
        // 対象商品が無料返品可能かどうかを判定する
        if (product.canFreeReturn()) {
            // 対象商品が無料返品可能のケース
            returnTransaction.setDeliveryCharge(0);
        } else {
            // 対象商品が無料返品不可のケース
            // 沖縄・小笠原など遠隔地かどうかを判定
            if (Util.isRemoteLocation(customer.getAddress())) {
                // 遠隔地のケース
                returnTransaction.setDeliveryCharge(1300);
            } else {
                // 遠隔地以外のケース
                returnTransaction.setDeliveryCharge(700);
            }
        }

        // RETURN_TRANSACTIONテーブルに挿入する
        // ReturnTransactionDAO.persist(returnTransaction);
    }
}
