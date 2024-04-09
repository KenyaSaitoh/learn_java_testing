package pro.kensait.spring.bookstore.web.customer;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

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
        @Size(min = 4, max = 10)
        String password,
        // 生年月日
        @DateTimeFormat(pattern = "yyyy/M/d")
        LocalDate birthday,
        // 住所
        @Size(min = 0, max = 40)
        String address) {
}