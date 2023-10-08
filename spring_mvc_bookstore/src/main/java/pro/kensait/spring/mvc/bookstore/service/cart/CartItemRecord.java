package pro.kensait.spring.mvc.bookstore.service.cart;

import java.math.BigDecimal;

public record CartItemRecord (
    Integer bookId,
    String bookName,
    String publisherName,
    BigDecimal price,
    Integer count,
    boolean remove) {
}