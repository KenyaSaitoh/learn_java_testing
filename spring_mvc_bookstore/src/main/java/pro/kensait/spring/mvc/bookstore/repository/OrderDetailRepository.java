package pro.kensait.spring.mvc.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.mvc.bookstore.entity.OrderDetail;
import pro.kensait.spring.mvc.bookstore.entity.OrderDetailPK;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}