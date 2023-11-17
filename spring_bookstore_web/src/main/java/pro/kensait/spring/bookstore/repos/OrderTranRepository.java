package pro.kensait.spring.bookstore.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.bookstore.entity.OrderTran;
import pro.kensait.spring.bookstore.service.order.OrderHistoryTO;

@Repository
public interface OrderTranRepository extends JpaRepository<OrderTran, Integer> {
    @Query("SELECT ot FROM OrderTran ot INNER JOIN ot.orderDetails od "
            + "WHERE ot.customer.customerId = :customerId "
            + "ORDER BY ot.orderDate, od.orderDetailId")
    List<OrderTran> findOrderTranByCustomer(@Param("customerId") Integer customerId);

    @Query("SELECT new pro.kensait.spring.bookstore.service.order.OrderHistoryTO "
            + "(ot.orderDate, ot.orderTranId, od.orderDetailId, od.book.bookName, "
            + "od.book.publisher.publisherName, od.price, od.count) "
            + "FROM OrderTran ot INNER JOIN ot.orderDetails od "
            + "WHERE ot.customer.customerId = :customerId "
            + "ORDER BY ot.orderDate, od.orderDetailId")
    List<OrderHistoryTO> findOrderHistoryTOByCustomer(
            @Param("customerId") Integer customerId);
}