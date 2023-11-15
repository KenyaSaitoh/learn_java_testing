package pro.kensait.spring.mvc.bookstore.web.cart;

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
import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.book.BookService;

@Controller
@SessionAttributes(types = {CartSession.class})
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

        Book book = bookService.find(bookId);

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

        // 合計金額を加算する
        BigDecimal totalPrice = cartSession.getTotalPrice();
        cartSession.setTotalPrice(totalPrice.add(book.getPrice()));

        return "CartViewPage";
    }

    // アクションメソッド： 選択された書籍をカートから削除する
    @PostMapping("/removeBook")
    public String removeBook(@RequestParam List<Integer> removeBookIdList,
            CartSession cartSession) {
        logger.info("[ CartController#removeBook ]");

        List<CartItem> cartItems = cartSession.getCartItems();
        BigDecimal totalPrice = cartSession.getTotalPrice();
        for (CartItem cartItem : cartItems) {
            INNER_LOOP : for (Integer bookId :  removeBookIdList) {
                if (cartItem.getBookId().equals(bookId)) {
                    // 合計金額を減算する
                    cartSession.setTotalPrice(totalPrice.subtract(cartItem.getPrice()));
                    //removeBookIdList.remove(bookId);
                    // カートから削除する
                    cartItems.remove(cartItem);
                    break INNER_LOOP;
                }
            }
        }
        // TODO 配送料を再計算する

        return "CartViewPage";
    }

    // アクションメソッド： カートをクリアする
    @PostMapping("/clear")
    public String clear(SessionStatus status) {
        logger.info("[ CartController#clear ]");

        status.setComplete(); // ログアウトはしないでCartSessionを削除することができる
        return "CartClearPage";
    }

    // アクションメソッド： カートを参照する
    @GetMapping("/viewCart")
    public String viewCart() {
        logger.info("[ CartController#viewCart ]");

        return "CartViewPage";
    }

    // アクションメソッド： カートの内容を確定する
    @PostMapping("/fix")
    public String fix(HttpSession httpSession, CartSession cartSession,
            Model model) {
        logger.info("[ CartController#fix ]");

        Customer customer = (Customer) httpSession.getAttribute("customer");

        // TODO これいる？
        // →@ModelAttributeで指定されたCartSessionはaddAttributeメソッド不要だが、
        // HttpSessionに自分で名前を付けて格納したオブジェクトはaddAttributeが必要！
        // → だが、Thymeleafで${session}使えばModelにわざわざ格納しなくても大丈夫
        //model.addAttribute("customer", customer);

        // デフォルトの配送先住所として、CustomerBeanオブジェクトの住所を設定する
        cartSession.setDeliveryAddress(customer.getAddress());

        // 配送料金を計算し、CartBeanオブジェクトに設定する 
        int totalCount = cartSession.getCartItems().size();
        cartSession.setDeliveryPrice(BigDecimal.valueOf(totalCount * 250));

        return "BookOrderPage";
    }
}