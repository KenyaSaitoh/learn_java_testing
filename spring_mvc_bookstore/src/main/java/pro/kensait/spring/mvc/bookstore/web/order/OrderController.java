package pro.kensait.spring.mvc.bookstore.web.order;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.order.OrderServiceIF;
import pro.kensait.spring.mvc.bookstore.service.order.OrderTO;
import pro.kensait.spring.mvc.bookstore.web.cart.CartSession;

@Controller
@SessionAttributes("cartSession")
public class OrderController {
    @Autowired
    private HttpSession session;

    @Autowired
    private OrderServiceIF orderService;

    // アクションメソッド（買い物カゴに入れた書籍を注文する）
    @PostMapping("/order")
    public String order(CartSession cartSession, SessionStatus sessionStatus) {
        // セッションマップからCustomerBeanオブジェクトを取得する
        Customer customer = (Customer) session.getAttribute("customer");

        // トランスファーオブジェクトを生成する
        OrderTO orderTO = new OrderTO(
                customer.getCustomerId(),
                LocalDate.now(),
                cartSession.getCartItems(),
                cartSession.getTotalPrice(),
                cartSession.getDeliveryPrice(),
                cartSession.getDeliveryAddress(),
                cartSession.getSettlementType());

        // セッションBeanのビジネスメソッド（注文処理）を呼び出す
        orderService.orderBooks(orderTO);

        // セッションマップからカートを削除する
        sessionStatus.setComplete();

        return "ThankYouPage";
    }
}