package pro.kensait.spring.bookstore.service.order;

import java.util.List;

import pro.kensait.spring.bookstore.entity.OrderDetail;
import pro.kensait.spring.bookstore.entity.OrderDetailPK;
import pro.kensait.spring.bookstore.entity.OrderTran;

public interface OrderServiceIF {
    List<OrderTran> getOrderHistory(Integer customerId);
    List<OrderHistoryTO> getOrderHistory2(Integer customerId);
    OrderTran getOrderTran(Integer tranId);
    OrderDetail getOrderDetail(OrderDetailPK pk);
    OrderTran orderBooks(OrderTO orderTO);
}