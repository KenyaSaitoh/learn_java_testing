package pro.kensait.spring.bookstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import pro.kensait.spring.bookstore.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Stock s WHERE s.bookId = :bookId")
    Stock findByIdWithLock(Integer bookId);

    @Modifying
    @Query("UPDATE Stock s SET s.quantity = s.quantity - :count, "
            + "s.version = :version + 1 "
            + "WHERE s.bookId = :bookId AND s.version = :version")
    Integer updateStock(@Param("bookId") Integer bookId,
            @Param("count") Integer count,
            @Param("version") Long version);
}