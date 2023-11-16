package pro.kensait.spring.mvc.bookstore.service.order;

import java.util.List;

import pro.kensait.spring.mvc.bookstore.entity.OrderDetail;
import pro.kensait.spring.mvc.bookstore.entity.OrderDetailPK;
import pro.kensait.spring.mvc.bookstore.entity.OrderTran;

public interface OrderServiceIF {
    List<OrderTran> getOrderHistory(Integer customerId);
    OrderDetail getOrderDetail(OrderDetailPK pk);
    void orderBooks(OrderTO orderTO);
}