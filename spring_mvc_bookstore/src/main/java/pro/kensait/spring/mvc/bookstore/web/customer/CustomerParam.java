package pro.kensait.spring.mvc.bookstore.web.customer;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record CustomerParam(
        // 顧客名
        @NotEmpty
        @Size(min = 1, max = 20)
        String customerName,
        // メールアドレス
        @NotEmpty
        String email,
        // パスワード
        @NotEmpty
        String password,
        // 生年月日
        LocalDate birthday,
        // 住所
        @Size(min = 1, max = 40)
        String address) {
}