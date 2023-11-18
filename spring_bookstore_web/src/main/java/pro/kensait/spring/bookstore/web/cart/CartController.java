package pro.kensait.spring.bookstore.web.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.bookstore.apiclient.CustomerTO;
import pro.kensait.spring.bookstore.entity.Book;
import pro.kensait.spring.bookstore.service.book.BookService;

@Controller
@SessionAttributes("cartSession")
public class CartController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    @ModelAttribute("cartSession")
    public CartSession initSession(){
        logger.info("[ CartController#initSession ]");

        return new CartSession(new CopyOnWriteArrayList<>(),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                null,
                null);
    }

    @Autowired
    private BookService bookService;

    // アクションメソッド： 書籍をカートに追加する
    @PostMapping("/addBook")
    public String addBook(@RequestParam("bookId") Integer bookId,
            CartSession cartSession) {
        logger.info("[ CartController#addBook ]");

        // サービスを呼び出して、選択されたIDから書籍エンティティを取得する
        Book book = bookService.getBook(bookId);

        // 選択された書籍がカートに存在している場合は、注文数と金額を加算する
        boolean isExists = false;
        List<CartItem> cartItems = cartSession.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (bookId.equals(cartItem.getBookId())) {
                cartItem.setCount(cartItem.getCount() + 1);
                cartItem.setPrice(cartItem.getPrice().add(book.getPrice()));
                isExists = true;
                break;
            }
        }

        // 選択された書籍がカートに存在していない場合は、
        // 新しいCartItemBeanオブジェクトを生成し、カートに追加する
        if (! isExists) {
            CartItem cartItem = new CartItem(book.getBookId(),
                    book.getBookName(),
                    book.getPublisher().getPublisherName(),
                    book.getPrice(),
                    1,
                    false);
            cartItems.add(cartItem);
        }
        cartSession.setCartItems(cartItems);

        // 合計金額を加算し、HTTPセッションに追加する
        BigDecimal totalPrice = cartSession.getTotalPrice();
        cartSession.setTotalPrice(totalPrice.add(book.getPrice()));

        // CartViewPageにフォワードする
        return "CartViewPage";
    }

    // アクションメソッド： 選択された書籍をカートから削除する
    @PostMapping("/removeBook")
    public String removeBook(@RequestParam List<Integer> removeBookIdList,
            CartSession cartSession) {
        logger.info("[ CartController#removeBook ]");

        // 選択された書籍があったかどうかを判定
        if (removeBookIdList != null) {
            // カート内の書籍と選択された書籍を、二重ループによってマッチングする
            List<CartItem> cartItems = cartSession.getCartItems();
            BigDecimal totalPrice = cartSession.getTotalPrice();
            for (CartItem cartItem : cartItems) {
                INNER_LOOP : for (Integer bookId :  removeBookIdList) {
                    if (cartItem.getBookId().equals(bookId)) {
                        // 合計金額を減算する
                        cartSession.setTotalPrice(totalPrice.subtract(cartItem.getPrice()));
                        removeBookIdList.remove(bookId);
                        // カートから削除する
                        cartItems.remove(cartItem);
                        break INNER_LOOP;
                    }
                }
            }
        }

        // CartViewPageにフォワードする
        return "CartViewPage";
    }

    // アクションメソッド： カートをクリアする
    @PostMapping("/clear")
    public String clear(SessionStatus status) {
        logger.info("[ CartController#clear ]");

        // HTTPセッションからCartSessionを削除する
        status.setComplete();

        // CartClearPageにフォワードする
        return "CartClearPage";
    }

    // アクションメソッド： カートを参照する
    @GetMapping("/viewCart")
    public String viewCart() {
        logger.info("[ CartController#viewCart ]");

        // CartViewPageにフォワードする
        return "CartViewPage";
    }

    // アクションメソッド： カートの内容を確定する
    @PostMapping("/fix")
    public String fix(HttpSession httpSession, CartSession cartSession,
            Model model) {
        logger.info("[ CartController#fix ]");

        CustomerTO customer = (CustomerTO) httpSession.getAttribute("customer");

        // デフォルトの配送先住所として、CartSessionに住所を設定する
        cartSession.setDeliveryAddress(customer.address());

        // 配送料金を計算し、CartSessionに設定する 
        // ※1個につき250円、4個以上は一律1000円
        int totalCount = cartSession.getCartItems().size();
        BigDecimal deliveryPrice = totalCount < 4 ?
                BigDecimal.valueOf(totalCount * 250) : 
                    BigDecimal.valueOf(1000);
        cartSession.setDeliveryPrice(deliveryPrice);

        // BookOrderPageにフォワードする
        return "BookOrderPage";
    }
}