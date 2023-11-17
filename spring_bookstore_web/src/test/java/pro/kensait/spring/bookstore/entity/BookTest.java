package pro.kensait.spring.bookstore.entity;

import org.junit.jupiter.api.Test;

import pro.kensait.spring.bookstore.entity.Book;
import pro.kensait.spring.bookstore.entity.base.JpaTestBase;

public class BookTest extends JpaTestBase {

    // 検索（主キーから）
    @Test
    public void test1() {
        Book book = em.find(Book.class, 1);
        System.out.println(book);
    }
}