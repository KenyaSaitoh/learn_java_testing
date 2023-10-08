package pro.kensait.spring.mvc.bookstore.service.order;

import java.util.List;

import pro.kensait.spring.mvc.bookstore.entity.Book;

public interface OrderServiceIF {
    List<Book> searchBook(Integer categoryId, String keyword);
    List<Book> searchBook(String keyword);
    void orderBooks(OrderRecord orderTO);
}