package pro.kensait.spring.mvc.bookstore.web.order;

import java.util.List;

import jakarta.faces.model.SelectItem;

import pro.kensait.spring.mvc.bookstore.web.cart.CartParam;

public record OrderParam(
        // カテゴリID
        int categoryId,
        // キーワード
        String keyword,
        // カテゴリリスト
        List<SelectItem> categoryList,
        // カート
        CartParam cart) {
}