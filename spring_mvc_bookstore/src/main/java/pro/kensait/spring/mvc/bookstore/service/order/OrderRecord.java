package pro.kensait.spring.mvc.bookstore.service.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import pro.kensait.spring.mvc.bookstore.service.cart.CartItemRecord;

public record OrderRecord (
    Integer customerId,
    LocalDate orderDate,
    List<CartItemRecord> cartItems,
    BigDecimal totalPrice,
    BigDecimal deliveryPrice,
    String deliveryAddress,
    Integer settlementType) {
}