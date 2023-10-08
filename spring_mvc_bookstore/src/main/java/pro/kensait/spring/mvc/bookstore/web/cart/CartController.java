package pro.kensait.spring.mvc.bookstore.web.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import pro.kensait.spring.mvc.bookstore.service.CartItemTO;
import pro.kensait.spring.mvc.bookstore.service.cart.CartItemRecord;

public class CartController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    // アクションメソッド（書籍をカートに追加する）
    public String addBook(Integer bookId, Model model) {

        // 選択された書籍を表すBookBeanオブジェクトを取得する
        BookForm bookBean = (BookForm) bookTable.getRowData();

        // 選択された書籍がカートに存在している場合は、注文数を加算する
        boolean isExists = false;
        Iterator<CartItemTO> i = getCartItems().iterator();
        while (i.hasNext()) {
            CartItemTO cartItem = i.next();
            if (bookBean.getBookId() == cartItem.getBookId()) {
                cartItem.setCount(cartItem.getCount() + 1);
                isExists = true;
            }
        }

        // 選択された書籍がカートに存在していない場合は、
        // 新しいCartItemBeanオブジェクトを生成し、カートに追加する
        if (!isExists) {
            CartItemRecord cartItem = new CartItemRecord(bookBean.getBookId(),
                    bookBean.getBookName(),
                    bookBean.getPublisherName(),
                    bookBean.getPrice(),
                    1);
            cartItems.add(cartItem);
        }

        // 合計金額を加算する
        totalPrice = totalPrice.add(bookBean.getPrice());

        return "success";
    }

    // アクションメソッド（選択された書籍をカートから削除する）
    public String remove() {
        Iterator<CartItemTO> i = cartItems.iterator();
        while (i.hasNext()) {
            CartItemTO cartItem = i.next();
            if (cartItem.isRemove()) {
                cartItems.remove(cartItem);
                // 合計金額を減算する
                totalPrice = totalPrice.subtract(
                        cartItem.getPrice().multiply(
                                BigDecimal.valueOf(cartItem.getCount())));
            }
        }

        return "success";
    }

    // アクションメソッド（カートをクリアする）
    public String clear() {
        // セッションマップを取得する
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();

        // セッションマップからカートを削除する
        sessionMap.remove("cart");

        return "success";
    }

    // アクションメソッド（カートの内容を確定する）
    public String fix() {
        // セッションマップを取得する
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();

        // セッションマップからCustomerBeanオブジェクトを取得する
        CustomerParam customer = (CustomerParam) sessionMap.get("customer");

        // デフォルトの配送先住所として、CustomerBeanオブジェクトの住所を
        // 設定する
        setDeliveryAddress(customer.getAddress());

        // 配送料金を計算し、CartBeanオブジェクトに設定する 
        int totalCount = getCartItems().size();
        setDeliveryPrice(BigDecimal.valueOf(totalCount * 250));

        return "success";
    }
}