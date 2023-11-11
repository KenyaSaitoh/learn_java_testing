package pro.kensait.spring.mvc.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_DETAIL")
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    // 注文取引ID
    @Id
    @Column(name = "ORDER_TRAN_ID",
            nullable = false)
    private Integer orderTranId;

    // 注文明細ID
    @Id
    @Column(name = "ORDER_DETAIL_ID",
            nullable = false)
    private Integer orderDetailId;

    // 注文明細
    @ManyToOne(targetEntity = OrderTran.class)
    @JoinColumn(name = "ORDER_TRAN_ID",
            referencedColumnName = "ORDER_TRAN_ID",
            insertable = false, updatable = false) // ここがポイント！JPA教材でもちゃんと説明する
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
    public OrderDetail(Integer orderTranId, Integer orderDetailId, Book book, 
            Integer count) {
        this.orderTranId = orderTranId;
        this.orderDetailId = orderDetailId;
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