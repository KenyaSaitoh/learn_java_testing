package pro.kensait.spring.mvc.bookstore.entity;

import org.junit.jupiter.api.Test;

import pro.kensait.spring.mvc.bookstore.entity.base.JpaTestBase;

public class BookTest extends JpaTestBase {

    // 検索（主キーから）
    @Test
    public void test1() {
        Book book = em.find(Book.class, 1);
        System.out.println(book);
    }
}