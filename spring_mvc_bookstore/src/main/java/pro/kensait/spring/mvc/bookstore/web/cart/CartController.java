package pro.kensait.spring.mvc.bookstore.web.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.book.BookService;

@Controller
@SessionAttributes("cartSession")
public class CartController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    @ModelAttribute("cartSession")
    public CartSession initSession(){
        logger.info("[ CartController#initSession ]");
        return new CartSession();
    }

    @Autowired
    private BookService bookService;
    
    // アクションメソッド（書籍をカートに追加する）
    public String addBook(Integer bookId, CartSession cartSession) {

        Book book = bookService.find(bookId);

        // 選択された書籍がカートに存在している場合は、注文数を加算する
        boolean isExists = false;
        List<CartItem> cartItems = cartSession.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (bookId == cartItem.getBookId()) {
                cartItem.setCount(cartItem.getCount() + 1);
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

        // 合計金額を加算する
        BigDecimal totalPrice = cartSession.getDeliveryPrice();
        cartSession.setDeliveryPrice(totalPrice.add(book.getPrice()));

        return "success";
    }

    // アクションメソッド（選択された書籍をカートから削除する）
    public String remove(CartSession cartSession) {
        List<CartItem> cartItems = cartSession.getCartItems();
        for (CartItem cartItem :  cartItems) {
            if (cartItem.isRemove()) {
                cartItems.remove(cartItem);
                // 合計金額を減算する
                BigDecimal totalPrice = cartSession.getDeliveryPrice();
                BigDecimal count = BigDecimal.valueOf(cartItem.getCount());
                cartSession.setDeliveryPrice(
                        totalPrice.subtract(
                                cartItem.getPrice().multiply(count)));
            }
        }

        return "success";
    }

    // アクションメソッド（カートをクリアする）
    public String clear(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // cartSessionのみが削除されるらしい
        return "success";
    }

    // アクションメソッド（カートの内容を確定する）
    public String fix(HttpSession httpSession, CartSession cartSession) {

        Customer customer = (Customer) httpSession.getAttribute("customer");

        // デフォルトの配送先住所として、CustomerBeanオブジェクトの住所を設定する
        cartSession.setDeliveryAddress(customer.getAddress());

        // 配送料金を計算し、CartBeanオブジェクトに設定する 
        int totalCount = cartSession.getCartItems().size();
        cartSession.setDeliveryPrice(BigDecimal.valueOf(totalCount * 250));

        return "success";
    }
}