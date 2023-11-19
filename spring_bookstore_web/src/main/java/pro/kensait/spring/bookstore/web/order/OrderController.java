package pro.kensait.spring.bookstore.web.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.bookstore.apiclient.CustomerTO;
import pro.kensait.spring.bookstore.entity.OrderDetail;
import pro.kensait.spring.bookstore.entity.OrderDetailPK;
import pro.kensait.spring.bookstore.entity.OrderTran;
import pro.kensait.spring.bookstore.service.order.OrderHistoryTO;
import pro.kensait.spring.bookstore.service.order.OrderServiceIF;
import pro.kensait.spring.bookstore.service.order.OrderTO;
import pro.kensait.spring.bookstore.service.order.OutOfStockException;
import pro.kensait.spring.bookstore.web.cart.CartSession;

@Controller
@SessionAttributes("cartSession")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(
            OrderController.class);

    @Autowired
    private OrderServiceIF orderService;

    @Autowired
    private HttpSession session;

    @Autowired
    private MessageSource messageSource;

    // アクションメソッド：注文履歴を表示する（方式1）
    @GetMapping("/viewHistory")
    public String viewHistory(Model model) {
        logger.info("[ OrderController#viewHistory ]");

        // HTTPセッションからCustomerTOを取得する
        CustomerTO customer = (CustomerTO) session.getAttribute("customer");

        // OrderServiceを呼び出して、注文エンティティのリスト（履歴）を取得する
        List<OrderTran> orderList =
                orderService.getOrderHistory(customer.customerId());

        // 取得した注文エンティティリストをフラットな構造に変換する
        List<OrderHistoryTO> orderHistoryList = new ArrayList<>(); 
        for (OrderTran orderTran : orderList) {
            for (OrderDetail orderDetail : orderTran.getOrderDetails()) {
            orderHistoryList.add(new OrderHistoryTO(
                    orderTran.getOrderDate(),
                    orderTran.getOrderTranId(),
                    orderDetail.getOrderDetailId(),
                    orderDetail.getBook().getBookName(),
                    orderDetail.getBook().getPublisher().getPublisherName(),
                    orderDetail.getPrice(),
                    orderDetail.getCount()));
            }
        }

        // 取得した注文エンティティリストをモデルに追加する
        model.addAttribute("orderHistoryList", orderHistoryList);

        // OrderHistoryPageにフォワードする
        return "OrderHistoryPage";
    }

    // アクションメソッド：注文履歴を表示する（方式2）
    @GetMapping("/viewHistory2")
    public String viewHistory2(Model model) {
        logger.info("[ OrderController#viewHistory2 ]");

        // HTTPセッションからCustomerTOを取得する
        CustomerTO customer = (CustomerTO) session.getAttribute("customer");

        // OrderServiceを呼び出して、注文エンティティのリスト（履歴）を取得する
        List<OrderHistoryTO> orderHistoryList =
                orderService.getOrderHistory2(customer.customerId());

        // 取得した注文エンティティリストをモデルに追加する
        model.addAttribute("orderHistoryList", orderHistoryList);

        // OrderHistoryPageにフォワードする
        return "OrderHistoryPage";
    }

    // アクションメソッド：注文履歴を表示する（方式3）
    @GetMapping("/viewHistory3")
    public String viewHistory3(Model model) {
        logger.info("[ OrderController#viewHistory3 ]");

        // HTTPセッションからCustomerTOを取得する
        CustomerTO customer = (CustomerTO) session.getAttribute("customer");

        // OrderServiceを呼び出して、注文エンティティのリスト（履歴）を取得する
        List<OrderTran> orderList =
                orderService.getOrderHistory(customer.customerId());

        // 取得した注文エンティティリストをモデルに追加する
        model.addAttribute("orderList", orderList);

        // OrderHistoryPage3にフォワードする
        return "OrderHistoryPage3";
    }

    // アクションメソッド：注文明細を表示する
    @GetMapping("/viewOrderDetail")
    public String viewOrderDetail(
            @RequestParam("tranId") Integer tranId,
            @RequestParam("detailId") Integer detailId,
            Model model) {
        logger.info("[ OrderController#viewOrderDetail ]");

        // OrderServiceを呼び出し、指定された注文明細エンティティを取得する
        OrderDetailPK pk = new OrderDetailPK(tranId, detailId);
        OrderDetail orderDetail = orderService.getOrderDetail(pk);

        // 取得した注文明細エンティティをモデルに追加する
        model.addAttribute("orderDetail", orderDetail);

        // OrderDetailPageにフォワードする
        return "OrderDetailPage";
    }

    // アクションメソッド：買い物カゴに入れた書籍を注文する
    @PostMapping("/order")
    public String order(@Validated CartSession cartSession,
            BindingResult errors,
            Model model,
            RedirectAttributes redirectAttributes,
            SessionStatus sessionStatus) {
        logger.info("[ OrderController#order ]");

        // 入力エラーがあったら、BookOrderPageにフォワードする
        if (errors.hasErrors()) {
            return "BookOrderPage";
        }

        // HTTPセッションからCustomerTOを取得する
        CustomerTO customer = (CustomerTO) session.getAttribute("customer");

        // トランスファーオブジェクトを生成する
        OrderTO orderTO = new OrderTO(
                customer.customerId(),
                LocalDate.now(),
                cartSession.getCartItems(),
                cartSession.getTotalPrice(),
                cartSession.getDeliveryPrice(),
                cartSession.getDeliveryAddress(),
                cartSession.getSettlementType());

        // OrderServiceを呼び出し、注文処理を実行する
        OrderTran orderTran = null;
        try {
            orderTran = orderService.orderBooks(orderTO);

        // 在庫不足があった場合はエラーメッセージをモデルに追加し、OrderErrorPageにフォワードする
        } catch(OutOfStockException oe) {
            logger.info("[ OrderController#order ] 在庫不足エラー");

            String errorMessage = messageSource.getMessage("error.order.outof-stock",
                    new Object[]{oe.getBookName()}, null);

            model.addAttribute("errorMessage",errorMessage);
            return "OrderErrorPage";

        // 他の顧客による更新があった場合（楽観ロックエラー）はエラーメッセージをモデルに追加し、
        // OrderErrorPageにフォワードする
        } catch(OptimisticLockException oe) {
            logger.info("[ OrderController#order ] 楽観ロックエラー");

            String errorMessage = messageSource.getMessage("error.order.optimistic-lock",
                    null, null);

            model.addAttribute("errorMessage",errorMessage);
            return "OrderErrorPage";
        }

        // 注文IDをRedirectAttributesに格納する（引き回し用）
        redirectAttributes.addAttribute("orderTranId", orderTran.getOrderTranId());

        // HTTPセッションからカートを削除する
        sessionStatus.setComplete();

        // OrderSuccessPageにリダイレクトする
        return "redirect:/viewOrderSuccess";
    }

    // アクションメソッド：注文に成功した内容を表示する
    @GetMapping("/viewOrderSuccess")
    public String viewOrderSuccess(
            @RequestParam("orderTranId") Integer orderTranId,
            Model model) {
        logger.info("[ OrderController#viewOrderSuccess ]");

        // OrderServiceを呼び出し、指定された注文エンティティを取得する
        OrderTran orderTran = orderService.getOrderTran(orderTranId);

        // 取得した注文明細エンティティをモデルに追加する
        model.addAttribute("orderTran", orderTran);

        // OrderSuccessPageにフォワードする
        return "OrderSuccessPage";
    }
}