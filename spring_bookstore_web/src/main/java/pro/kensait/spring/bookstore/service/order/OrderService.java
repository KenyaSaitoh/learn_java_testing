package pro.kensait.spring.bookstore.service.order;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.OptimisticLockException;
import pro.kensait.spring.bookstore.entity.Book;
import pro.kensait.spring.bookstore.entity.OrderDetail;
import pro.kensait.spring.bookstore.entity.OrderDetailPK;
import pro.kensait.spring.bookstore.entity.OrderTran;
import pro.kensait.spring.bookstore.entity.Stock;
import pro.kensait.spring.bookstore.repos.BookRepository;
import pro.kensait.spring.bookstore.repos.OrderDetailRepository;
import pro.kensait.spring.bookstore.repos.OrderTranRepository;
import pro.kensait.spring.bookstore.repos.StockRepository;
import pro.kensait.spring.bookstore.web.cart.CartItem;

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
    private StockRepository stockRepository;

    // サービスメソッド：注文エンティティのリストを取得する
    @Override
    public List<OrderTran> getOrderHistory(Integer customerId) {
        logger.info("[ OrderService#findOrderHistory ]");

        // 顧客IDから注文エンティティのリストを取得し、返す
        List<OrderTran> orderTranList =
                orderTranRepos.findOrderTranByCustomer(customerId);
        return orderTranList;
    }

    // サービスメソッド：注文エンティティのリストを取得する
    @Override
    public List<OrderHistoryTO> getOrderHistory2(Integer customerId) {
        logger.info("[ OrderService#findOrderHistory2 ]");

        // 顧客IDから注文エンティティのリストを取得し、返す
        List<OrderHistoryTO> orderHistoryList =
                orderTranRepos.findOrderHistoryTOByCustomer(customerId);
        return orderHistoryList;
    }

    // サービスメソッド：注文明細エンティティを取得する
    @Override
    public OrderTran getOrderTran(Integer orderTranId) {
        logger.info("[ OrderService#getOrderTran ]");

        // 主キー（注文ID）から注文エンティティを取得し、返す
        Optional<OrderTran> orderTranOpt = orderTranRepos.findById(orderTranId);
        return orderTranOpt.orElseThrow();
    }

    // サービスメソッド：注文明細エンティティを取得する
    @Override
    public OrderDetail getOrderDetail(OrderDetailPK pk) {
        logger.info("[ OrderService#getOrderDetail ]");

        // 複合主キー（注文IDと注文明細ID）から注文明細エンティティを取得し、返す
        Optional<OrderDetail> orderDetailOpt = orderDetailRepos.findById(pk);
        return orderDetailOpt.orElseThrow();
    }

    // サービスメソッド：注文する
    @Override
    public OrderTran orderBooks(OrderTO orderTO) {
        logger.info("[ OrderService#orderBooks ]");

        // カートに追加された書籍毎に、在庫の残り個数をチェックする
        for (CartItem cartItem : orderTO.cartItems()) {

            // 書籍IDをキーに在庫テーブルから在庫エンティティを取得する
            Stock stock = stockRepository.findByIdWithLock(cartItem.getBookId());

            // 在庫が0未満になる場合は、例外を送出する
            int remaining = stock.getQuantity() - cartItem.getCount();
            if (remaining < 0) {
                throw new OutOfStockException(
                        cartItem.getBookId(),
                        cartItem.getBookName(),
                        "在庫不足");
            }

            // 他の顧客によって在庫が更新されていた場合は、例外を送出する
            int updateCount = stockRepository.updateStock(
                            cartItem.getBookId(),
                            cartItem.getCount(),
                            stock.getVersion());
            if (updateCount != 1) {
                throw new OptimisticLockException("別の顧客によって在庫が更新されています");
            }
        }

        // 新しいOrderTranインスタンスを生成する
        OrderTran orderTran = new OrderTran(
                orderTO.orderDate(),
                orderTO.customerId(),
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

        // 更新済みのOrderTranを返す
        return orderTran;
    }
}