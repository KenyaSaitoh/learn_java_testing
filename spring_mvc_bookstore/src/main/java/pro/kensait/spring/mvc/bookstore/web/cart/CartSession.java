package pro.kensait.spring.mvc.bookstore.web.cart;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CartSession {
    // カートアイテムのリスト
    private List<CartItem> cartItems;
    // 注文金額合計
    private BigDecimal totalPrice;
    // 配送料金
    private BigDecimal deliveryPrice;
    // 配送先住所
    @NotEmpty
    @Size(min = 1, max = 40)
    private String deliveryAddress;
    // 決済方法
    @NotNull
    private Integer settlementType;

    // 引数の無いコンストラクタ
    public CartSession() {
    }

    // 全フィールドを引数にとるコンストラクタ
    public CartSession(List<CartItem> cartItems, BigDecimal totalPrice,
            BigDecimal deliveryPrice, String deliveryAddress, Integer settlementType) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
        this.deliveryAddress = deliveryAddress;
        this.settlementType = settlementType;
    }

    // アクセサメソッド
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
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

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    @Override
    public String toString() {
        return "CartSession [cartItems=" + cartItems + ", totalPrice=" + totalPrice
                + ", deliveryPrice=" + deliveryPrice + ", deliveryAddress="
                + deliveryAddress + ", settlementType=" + settlementType + "]";
    }
}