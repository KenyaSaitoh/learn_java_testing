package pro.kensait.spring.mvc.bookstore.web.order;

import java.time.LocalDate;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    private OrderServiceIF orderService;

    // アクションメソッド（買い物カゴに入れた書籍を注文する）
    @PostMapping("/order")
    public String order(@Validated CartSession cartSession, BindingResult errors,
            HttpSession session,
            SessionStatus sessionStatus,
            Customer customer2) {

        // セッションマップからCustomerBeanオブジェクトを取得する
        Customer customer = (Customer) session.getAttribute("customer");

        if (errors.hasErrors()) {
            return "BookOrderPage";
        }

        // トランスファーオブジェクトを生成する
        OrderTO orderTO = new OrderTO(
                customer.getCustomerId(),
                LocalDate.now(),
                cartSession.getCartItems(),
                cartSession.getTotalPrice(),
                cartSession.getDeliveryPrice(),
                cartSession.getDeliveryAddress(),
                cartSession.getSettlementType());

        // サービスのビジネスメソッド（注文処理）を呼び出す
        orderService.orderBooks(orderTO);

        // セッションマップからカートを削除する
        session.removeAttribute("cartSession");

        // これだとログアウトしちゃう
        // sessionStatus.setComplete();

        return "ThankYouPage";
    }
}