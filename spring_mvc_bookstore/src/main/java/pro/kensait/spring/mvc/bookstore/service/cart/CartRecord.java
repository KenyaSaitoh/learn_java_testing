package pro.kensait.spring.mvc.bookstore.service.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartRecord (
    // カートアイテムのリスト
    List<CartItemRecord> cartItems,
    // 注文金額合計
    BigDecimal totalPrice,
    // 配送料金
    BigDecimal deliveryPrice,
    // 配送先住所
    String deliveryAddress,
    // 決済方法
    Integer settlementType) {
}