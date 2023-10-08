package pro.kensait.spring.mvc.bookstore.web.order;

import java.util.List;

import javax.faces.model.SelectItem;

import pro.kensait.spring.mvc.bookstore.web.cart.CartParam;

public class OrderParam {
    // カテゴリ番号
    private int categoryId;
    // キーワード
    private String keyword;
    // カテゴリリスト
    private List<SelectItem> categoryList;
    // カート
    private CartParam cart;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<SelectItem> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<SelectItem> categoryList) {
        this.categoryList = categoryList;
    }

    public CartParam getCart() {
        return cart;
    }

    public void setCart(CartParam cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "OrderParam [categoryId=" + categoryId + ", keyword=" + keyword
                + ", categoryList=" + categoryList + ", cart=" + cart + "]";
    }
}