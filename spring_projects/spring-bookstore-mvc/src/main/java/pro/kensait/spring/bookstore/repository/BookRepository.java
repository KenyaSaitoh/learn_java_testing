package pro.kensait.spring.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.category.categoryId = :categoryId")
    List<Book> query(@Param("categoryId") Integer categoryId);

    @Query("SELECT b FROM Book b WHERE b.bookName like :keyword")
    List<Book> query(@Param("keyword") String keyword);

    @Query("SELECT b FROM Book b WHERE b.category.categoryId = :categoryId "
            + "AND b.bookName like :keyword")
    List<Book> query(@Param("categoryId") Integer categoryId,
            @Param("keyword") String keyword);
}