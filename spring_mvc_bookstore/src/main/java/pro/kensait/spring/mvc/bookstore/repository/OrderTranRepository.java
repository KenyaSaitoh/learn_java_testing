package pro.kensait.spring.mvc.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.mvc.bookstore.entity.OrderTran;

@Repository
public interface OrderTranRepository extends JpaRepository<OrderTran, Integer> {
}