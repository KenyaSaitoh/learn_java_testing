package pro.kensait.spring.mvc.bookstore.web.cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pro.kensait.spring.mvc.bookstore.service.cart.CartItemRecord;

public class CartParam {
    // カートアイテムのリスト
    private List<CartItemRecord> cartItems = new CopyOnWriteArrayList<CartItemRecord>();
    // 注文金額合計
    private BigDecimal totalPrice;
    // 配送料金
    private BigDecimal deliveryPrice;
    // 配送先住所
    private String deliveryAddress;
    // 決済方法
    private Integer settlementType;

    public List<CartItemRecord> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemRecord> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(int settlementType) {
        this.settlementType = settlementType;
    }

    @Override
    public String toString() {
        return "CartForm [cartItems=" + cartItems + ", totalPrice=" + totalPrice
                + ", deliveryPrice=" + deliveryPrice + ", deliveryAddress="
                + deliveryAddress + ", settlementType=" + settlementType + "]";
    }
}