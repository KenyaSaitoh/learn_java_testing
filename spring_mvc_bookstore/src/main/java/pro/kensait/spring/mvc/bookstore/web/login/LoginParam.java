package pro.kensait.spring.mvc.bookstore.web.login;

import jakarta.validation.constraints.NotNull;

public record LoginParam(
        // メールアドレス
        @NotNull
        String email,
        // パスワード
        @NotNull
        String password) {
}