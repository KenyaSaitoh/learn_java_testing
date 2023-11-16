package pro.kensait.spring.mvc.bookstore.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_TRAN")
public class OrderTran {
    // 注文ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ORDER_TRAN_ID")
    private Integer orderTranId;

    // 注文日付
    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    // 顧客
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID",
            referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    // 注文明細
    @OneToMany(targetEntity = OrderDetail.class,
            mappedBy = "orderTran")
    private List<OrderDetail> orderDetails;

    // 注文金額合計
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    // 配送料金
    @Column(name = "DELIVERY_PRICE")
    private BigDecimal deliveryPrice;

    // 配送先住所
    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    // 決済方法へのアクセサメソッド
    @Column(name = "SETTLEMENT_TYPE")
    private Integer settlementType;

    // 引数なしのコンストラクタ
    public OrderTran() {
    }

    // コンストラクタ
    public OrderTran(LocalDate orderDate, Customer customer, BigDecimal totalPrice, 
            BigDecimal deliveryPrice, String deliveryAddress, Integer settlementType) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
        this.deliveryAddress = deliveryAddress;
        this.settlementType = settlementType;
    }

    public Integer getOrderTranId() {
        return orderTranId;
    }

    public void setOrderTranId(Integer orderTranId) {
        this.orderTranId = orderTranId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
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
        return "OrderTran [orderTranId=" + orderTranId + ", orderDate=" + orderDate
                + ", customer=" + customer + ", orderDetails=" + orderDetails
                + ", totalPrice=" + totalPrice + ", deliveryPrice=" + deliveryPrice
                + ", deliveryAddress=" + deliveryAddress + ", settlementType="
                + settlementType + "]";
    }
}