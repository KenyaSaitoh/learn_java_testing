package pro.kensait.spring.bookstore.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.bookstore.entity.OrderDetail;
import pro.kensait.spring.bookstore.entity.OrderDetailPK;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}