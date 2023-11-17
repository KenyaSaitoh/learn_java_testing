package pro.kensait.spring.bookstore.web.cart;

import java.math.BigDecimal;

public class CartItem {
    // 書籍ID
    private Integer bookId;
    // 書籍名
    private String bookName;
    // 出版社名
    private String publisherName;
    // 価格
    private BigDecimal price;
    // 個数
    private Integer count;
    // 削除フラグ
    private boolean remove;

    // 引数の無いコンストラクタ
    public CartItem() {
    }

    // すべてのフィールドをパラメータに持つコンストラクタ
    public CartItem(Integer bookId, String bookName, String publisherName,
            BigDecimal price, Integer count, boolean remove) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisherName = publisherName;
        this.price = price;
        this.count = count;
        this.remove = remove;
    }

    // アクセサメソッド
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    @Override
    public String toString() {
        return "CartItem [bookId=" + bookId + ", bookName=" + bookName
                + ", publisherName=" + publisherName + ", price=" + price + ", count="
                + count + ", remove=" + remove + "]";
    }
}
