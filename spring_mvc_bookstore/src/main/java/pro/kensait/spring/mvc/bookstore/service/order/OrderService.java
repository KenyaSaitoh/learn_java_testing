package pro.kensait.spring.mvc.bookstore.service.order;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.entity.OrderDetail;
import pro.kensait.spring.mvc.bookstore.entity.OrderDetailPK;
import pro.kensait.spring.mvc.bookstore.entity.OrderTran;
import pro.kensait.spring.mvc.bookstore.repository.BookRepository;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;
import pro.kensait.spring.mvc.bookstore.repository.OrderDetailRepository;
import pro.kensait.spring.mvc.bookstore.repository.OrderTranRepository;
import pro.kensait.spring.mvc.bookstore.web.cart.CartItem;

@Service
@Transactional
public class OrderService implements OrderServiceIF {
    private static final Logger logger = LoggerFactory.getLogger(
            OrderService.class);

    @Autowired
    private OrderTranRepository orderTranRepos;

    @Autowired
    private OrderDetailRepository orderDetailRepos;

    @Autowired
    private BookRepository bookRepos;

    @Autowired
    private CustomerRepository customerRepos;

    // サービスメソッド： 注文エンティティのリストを取得する
    @Override
    public List<OrderTran> getOrderHistory(Integer customerId) {
        logger.info("[ OrderService#findOrderHistory ]");

        // 顧客IDから注文エンティティのリストを取得し、返す
        List<OrderTran> orderTranList =
                orderTranRepos.findOrderTranByCustomer(customerId);
        return orderTranList;
    }

    // サービスメソッド： 注文エンティティのリストを取得する
    @Override
    public List<OrderHistoryTO> getOrderHistory2(Integer customerId) {
        logger.info("[ OrderService#findOrderHistory2 ]");

        // 顧客IDから注文エンティティのリストを取得し、返す
        List<OrderHistoryTO> orderHistoryList =
                orderTranRepos.findOrderHistoryTOByCustomer(customerId);
        return orderHistoryList;
    }

    // サービスメソッド： 注文明細エンティティを取得する
    @Override
    public OrderDetail getOrderDetail(OrderDetailPK pk) {
        logger.info("[ OrderService#getOrderTran ]");

        // 複合主キー（注文IDと注文明細ID）から注文明細エンティティを取得し、返す
        Optional<OrderDetail> orderDetailOpt = orderDetailRepos.findById(pk);
        return orderDetailOpt.orElseThrow();
    }

    // サービスメソッド： 注文する
    @Override
    public void orderBooks(OrderTO orderTO) {
        logger.info("[ OrderService#orderBooks ]");

        // 顧客IDをキーにデータベースから顧客を検索する
        Customer customer = customerRepos.findById(orderTO.customerId()).get();

        // 新しいOrderTranインスタンスを生成する
        OrderTran orderTran = new OrderTran(
                orderTO.orderDate(),
                customer,
                orderTO.totalPrice(),
                orderTO.deliveryPrice(),
                orderTO.deliveryAddress(),
                orderTO.settlementType());

        // 生成したOrderTranインスタンスをpersist操作により永続化する
        orderTranRepos.save(orderTran);

        // カートアイテム（個々の注文明細）のイテレータを取得する
        List<CartItem> cartItems = orderTO.cartItems();

        // OrderDetailインスタンスの主キー値（注文明細ID）の初期値を設定する
        int orderDetailId = 0;

        for (CartItem cartItem : cartItems) {
            Book book = bookRepos.findById(cartItem.getBookId()).get();

            // OrderDetailインスタンスの主キー値（注文明細ID）をカウントアップする
            orderDetailId = orderDetailId + 1;

            // 新しいOrderDetailインスタンスを生成する
            OrderDetail orderDetail = new OrderDetail(
                    orderTran.getOrderTranId(),
                    orderDetailId,
                    book,
                    cartItem.getCount());

            // OrderDetailインスタンスを保存する
            orderDetailRepos.save(orderDetail);
        }
    }
}