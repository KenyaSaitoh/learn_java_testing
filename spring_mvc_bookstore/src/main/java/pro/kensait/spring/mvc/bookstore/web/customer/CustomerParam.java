package pro.kensait.spring.mvc.bookstore.web.customer;

import java.time.LocalDate;

public record CustomerParam(
        // 顧客ID
        Integer customerId,
        // 顧客名
        String customerName,
        // メールアドレス
        String email,
        // パスワード
        String password,
        // 生年月日
        LocalDate birthday,
        // 住所
        String address) {
}