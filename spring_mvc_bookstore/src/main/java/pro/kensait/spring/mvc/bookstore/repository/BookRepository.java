package pro.kensait.spring.mvc.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.mvc.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book AS b WHERE b.bookName like :keyword")
    List<Book> searchBook(@Param("keyword") String keyword);
}