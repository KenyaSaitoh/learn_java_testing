package pro.kensait.spring.bookstore.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock {
    // 書籍ID
    @Id
    @Column(name = "BOOK_ID")
    private Integer bookId;

    // 在庫数
    @Column(name = "QUANTITY")
    private Integer quantity;

    // バージョン
    @Column(name = "VERSION")
    private Long version;

    public Stock(Integer bookId, Integer quantity, Long version) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.version = version;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Stock [bookId=" + bookId + ", quantity=" + quantity + ", version="
                + version + "]";
    }
}