package pro.kensait.spring.mvc.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAIL")
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    // 注文取引番号
    @Id
    @Column(name = "ORDER_TRAN_ID",
            nullable = false, insertable = false, updatable = false)
    private Integer orderTranId;

    // 注文明細番号
    @Id
    @Column(name = "ORDER_DETAIL_ID",
            nullable = false)
    private Integer orderDetailId;

    // 注文明細
    @ManyToOne(targetEntity = OrderTran.class)
    @JoinColumn(name = "ORDER_TRAN_ID",
            referencedColumnName = "ORDER_TRAN_ID")
    private OrderTran orderTran;

    // 書籍
    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "BOOK_ID",
            referencedColumnName = "BOOK_ID")
    private Book book;

    // 注文数
    @Column(name = "COUNT")
    private Integer count;

    // 引数なしのコンストラクタ
    public OrderDetail() {
    }

    // コンストラクタ
    public OrderDetail(OrderTran orderTran, Integer orderDetailId, Book book, 
            Integer count) {
        this.orderTranId = orderTran.getOrderTranId();
        this.orderDetailId = orderDetailId;
        this.orderTran = orderTran;
        this.book = book;
        this.count = count;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderTran getOrderTran() {
        return orderTran;
    }

    public void setOrderTran(OrderTran orderTran) {
        this.orderTran = orderTran;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderDetail [orderDetailId=" + orderDetailId + ", orderTran=" + orderTran
                + ", book=" + book + ", count=" + count + "]";
    }
}