package pro.kensait.spring.mvc.bookstore.service.order;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.entity.OrderDetail;
import pro.kensait.spring.mvc.bookstore.entity.OrderTran;
import pro.kensait.spring.mvc.bookstore.repository.BookRepository;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;
import pro.kensait.spring.mvc.bookstore.repository.OrderDetailRepository;
import pro.kensait.spring.mvc.bookstore.repository.OrderTranRepository;
import pro.kensait.spring.mvc.bookstore.service.cart.CartItemRecord;

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

    // ビジネスメソッド（書籍検索処理）
    public List<Book> searchBook(Integer categoryId, String keyword) {
        // カテゴリとキーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(keyword);
    }

    // ビジネスメソッド（書籍検索処理）
    public List<Book> searchBook(String keyword) {
        // キーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(keyword);
    }

    // ビジネスメソッド（注文処理）
    public void orderBooks(OrderRecord order) {
        // 顧客番号をキーにデータベースから顧客を検索する
        Customer customer = customerRepos.findById(order.customerId()).get();

        // 新しいOrderTranインスタンスを生成する
        OrderTran orderTran = new OrderTran(
                order.orderDate(), customer, order.totalPrice(),
                order.deliveryPrice(), order.deliveryAddress(),
                order.settlementType());

        // 生成したOrderTranインスタンスをpersist操作により永続化する
        orderTranRepos.save(orderTran);

        // カートアイテム（個々の注文明細）のイテレータを取得する
        List<CartItemRecord> cartItems = order.cartItems();
        Iterator<CartItemRecord> i = cartItems.iterator();

        // OrderDetailインスタンスの主キー値（注文明細番号）の初期値を設定する
        int orderDetailId = 0;

        while (i.hasNext()) {
            CartItemRecord cartItem = i.next();

            // 書籍番号をキーにデータベースから書籍を検索する
            Book book = bookRepos.findById(cartItem.bookId()).get();

            // OrderDetailインスタンスの主キー値（注文明細番号）をカウントアップ
            orderDetailId = orderDetailId + 1;

            // 新しいOrderDetailインスタンスを生成する
            OrderDetail orderDetail = new OrderDetail(orderTran, orderDetailId,
                    book, cartItem.count());

            // 生成したOrderTranオブジェクトをpersist操作により永続化する
            orderDetailRepos.save(orderDetail);
        }
    }
}