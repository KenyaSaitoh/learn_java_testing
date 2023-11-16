package pro.kensait.spring.mvc.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.mvc.bookstore.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query("UPDATE Stock s SET s.quantity = s.quantity - :count, "
            + "s.version = :version + 1 "
            + "WHERE s.bookId = :bookId AND s.version = :version")
    Integer updateStock(@Param("bookId") Integer bookId,
            @Param("count") Integer count,
            @Param("version") Long version);
}