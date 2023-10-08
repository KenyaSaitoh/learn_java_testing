package pro.kensait.spring.mvc.bookstore.entity;

import java.io.Serializable;

public class OrderDetailPK implements Serializable {
    private Integer orderTranId;
    private Integer orderDetailId;

    // コンストラクタ
    public OrderDetailPK(int orderTranId, int orderDetailId) {
        this.orderTranId = orderTranId;
        this.orderDetailId = orderDetailId;
    }

    // 注文取引番号へのアクセサメソッド（ゲッタのみ）
    public Integer getOrderTranId() {
        return orderTranId;
    }

    // 注文明細番号へのアクセサメソッド（ゲッタのみ）
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    // 一意性を保証するために、必ずequalsメソッドをオーバーライドする
    public boolean equals(Object obj) {
        return ((obj instanceof OrderDetailPK) &&
                orderTranId == (((OrderDetailPK)obj).getOrderTranId()) &&
                orderDetailId == (((OrderDetailPK)obj).getOrderDetailId()));
    }

    // equalsメソッドに合わせて、hashcodeメソッドもオーバーライドする
    public int hashCode() {
        return orderTranId + orderDetailId;
    }
}